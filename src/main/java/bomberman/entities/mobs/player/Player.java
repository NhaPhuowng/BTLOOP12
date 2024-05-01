package bomberman.entities.mobs.player;

import bomberman.control.Collision;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.items.Item;
import bomberman.entities.items.Portal;
import bomberman.entities.mobs.Mob;
import bomberman.graphics.Sprite;
import bomberman.utils.MapLoader;
import bomberman.utils.Sound;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

import static bomberman.control.Movement.*;
import static bomberman.control.Status.*;
import static bomberman.entities.bomb.Bomb.*;

public class Player extends Mob {
    public static Pair<Integer, Integer> pos = new Pair<>(1, 1);
    private boolean keepUp;
    private boolean keepLeft;
    private boolean keepDown;
    private boolean keepRight;
    private boolean isDieCompleted;
    private boolean passBrick;
    private boolean passBomb;

    public Player(double x, double y) {
        super(Sprite.player_right.getFxImage(), x, y);
        init();
    }

    public void init() {
        timerAnimation = 0;
        frameAnimation = 0;
        timerAnimationMax = 5;
        deltaSprite = 0.125d;
        isLive = true;
        vel = 0.06d;
        status = RIGHT;
        isDieCompleted = false;
        passBrick = false;
        passBomb = false;
    }

    @Override
    public void loadImage() {
        leftSprite.add(Sprite.player_left.getFxImage());
        leftSprite.add(Sprite.player_left_1.getFxImage());
        leftSprite.add(Sprite.player_left_2.getFxImage());

        rightSprite.add(Sprite.player_right.getFxImage());
        rightSprite.add(Sprite.player_right_1.getFxImage());
        rightSprite.add(Sprite.player_right_2.getFxImage());

        upSprite.add(Sprite.player_up.getFxImage());
        upSprite.add(Sprite.player_up_1.getFxImage());
        upSprite.add(Sprite.player_up_2.getFxImage());

        downSprite.add(Sprite.player_down.getFxImage());
        downSprite.add(Sprite.player_down_1.getFxImage());
        downSprite.add(Sprite.player_down_2.getFxImage());
    }

    public boolean isPassBrick() {
        return passBrick;
    }

    public void setPassBrick(boolean passBrick) {
        this.passBrick = passBrick;
    }

    public boolean isPassBomb() {
        return passBomb;
    }

    public void setPassBomb(boolean passBomb) {
        this.passBomb = passBomb;
    }

    /**
     * Xử lí sự kiện di chuyển hoặc đặt bom.
     */
    public void playerAct(KeyEvent e) {
        if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
            keepUp = true;
        }
        if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
            keepLeft = true;
        }
        if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
            keepDown = true;
        }
        if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
            keepRight = true;
        }
        if (e.getCode() == KeyCode.SPACE) {
            putBomb(this);
        }
    }

    /**
     * Xử lí sự kiện khi nhả phím.
     */
    public void changeKeepMove(KeyEvent e) {
        switch (e.getCode()) {
            case W, UP -> {
                keepUp = false;
            }
            case A, LEFT -> {
                keepLeft = false;
            }
            case S, DOWN -> {
                keepDown = false;
            }
            case D, RIGHT -> {
                keepRight = false;
            }
        }
    }

    /**
     * Update di chuyển của nhân vật.
     */
    public void updateMovement() {
        //move
        if (keepUp) {
            moveUp(this);
        }
        if (keepLeft) {
            moveLeft(this);
        }
        if (keepDown) {
            moveDown(this);
        }
        if (keepRight) {
            moveRight(this);
        }

        // Nhân vật khi không di chuyển
        boolean isStand = !keepRight && !keepDown && !keepUp && !keepLeft;
        if (isStand) {
            timerAnimation = 0;
            frameAnimation = 1;
            switch (status) {
                case UP -> setImage(Sprite.player_up.getFxImage());
                case LEFT -> setImage(Sprite.player_left.getFxImage());
                case DOWN -> setImage(Sprite.player_down.getFxImage());
                case RIGHT -> setImage(Sprite.player_right.getFxImage());
            }
        }
    }

    /**
     * Vị trí của nhân vật trên map (đã làm tròn).
     */
    public void updatePositionOnBoard() {
        pos = new Pair<>((int) Math.round(y), (int) Math.round(x));
    }

    /**
     * Buff hiệu ứng khi nhân vật nhặt item.
     */
    public void receiveItem() {
        Item receivedItem = Collision.collideWithItem(this);
        if (receivedItem != null
                && MapLoader.board[(int) receivedItem.getY()][(int) receivedItem.getX()] != 3) {
            receivedItem.buff(this);
            if (!(receivedItem instanceof Portal)) {
                new Sound("clickPauseButton");
            }
        }
    }

    /**
     * Kiểm tra nhân vật có thể đi qua bom không.
     */
    private void passThroughBomb() {
        if (Collision.collideWithBomb(this) == null && !passBomb) {
            Bomb.setAllPassableFalse();
        }
    }

    @Override
    public void checkDie() {
        if (Collision.collideWithFlame(this) || Collision.collideWithEnemy(this)) {
            isLive = false;
        }
        if (!isLive) {
            if (status != DIE) {
                new Sound("die");
                this.frameAnimation = 0;
                this.timerAnimation = 0;
                this.timerAnimationMax = 15;
            }
            status = DIE;
            die();
        }
    }

    @Override
    public void die() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.player_dead1.getFxImage());
            case 1 -> setImage(Sprite.player_dead2.getFxImage());
            case 2 -> {
                setImage(Sprite.player_dead3.getFxImage());
                if (timerAnimation == timerAnimationMax) {
                    removeEntities.add(this);
                    isDieCompleted = true;
                }
            }
        }
    }

    /**
     * Kiểm tra liệu nhân vật đã die và load hết frame die chưa.
     */
    public boolean checkDieCompleted() {
        return isDieCompleted;
    }

    @Override
    public void update() {
        checkDie();
        if (isLive) {
            updatePositionOnBoard();
            receiveItem();
            passThroughBomb();
            updateMovement();
        }
        updateAnimation();
    }
}

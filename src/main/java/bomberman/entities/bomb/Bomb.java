package bomberman.entities.bomb;

import bomberman.control.Collision;
import bomberman.entities.Entity;
import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;
import bomberman.utils.MapLoader;
import bomberman.utils.Sound;
import javafx.scene.image.Image;

import java.util.Collections;

public class Bomb extends Entity {
    public static int maxBomb = 1;
    public static int puttedBomb;
    private final int timeToBoom = 100;
    private int timer;
    private boolean passable;

    public Bomb(Image img, double x, double y) {
        super(img, x, y);
        init();
    }

    private void init() {
        timerAnimationMax = 10;
        timer = 0;
        passable = true;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    /**
     * Không thể đi qua mọi quả bomb.
     */
    public static void setAllPassableFalse() {
        entities.forEach(e -> {
            if (e instanceof Bomb) {
                ((Bomb) e).setPassable(false);
            }
        });
    }

    private static boolean canPutBomb(Bomb newBomb) {
        if (puttedBomb >= maxBomb) {
            return false;
        }
        return !Collision.collideWithEnemy(newBomb)
                && (Collision.collideWithBomb(newBomb) == newBomb)
                && !(Collision.collideWithBrick(newBomb));
    }

    public static void putBomb(Player p) {
        Bomb newBomb = new Bomb(Sprite.bomb.getFxImage(), Math.round(p.getX()), Math.round(p.getY()));
        if (!canPutBomb(newBomb)) {
            // Remove new bomb
            entities.remove(entities.size() - 1);
            return;
        } else {
            new Sound("putBomb");
        }
        // Make bomb below character
        Collections.swap(entities, entities.size() - 1, entities.indexOf(p));
        MapLoader.board[(int) Math.round(newBomb.getRectY())][(int) Math.round(newBomb.getRectX())] = 2;
        puttedBomb++;
    }

    private void waitBoom() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.bomb.getFxImage());
            case 1 -> setImage(Sprite.bomb_1.getFxImage());
            case 2 -> setImage(Sprite.bomb_2.getFxImage());
        }
    }

    /**
     * Kích hoạt bom nổ, tạo flame.
     */
    private void boom() {
        new Flame(this.x, this.y);
        removeEntities.add(this);
        MapLoader.board[(int) Math.round(this.getRectY())][(int) Math.round(this.getRectX())] = 0;
        puttedBomb--;
        new Sound("explosion");
    }

    @Override
    public void update() {
        timer++;
        if (Collision.collideWithFlame(this)) {
            timer = timeToBoom - 2;
        }
        if (timer >= timeToBoom) {
            boom();
        } else {
            waitBoom();
        }
        updateAnimation();
    }
}

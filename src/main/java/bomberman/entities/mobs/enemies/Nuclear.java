package bomberman.entities.mobs.enemies;

import bomberman.graphics.Sprite;

import static bomberman.control.Status.LEFT;

public class Nuclear extends Enemy {
    public Nuclear(int x, int y) {
        super(Sprite.nuclear_left1.getFxImage(), x, y);
        init();
    }

    private void init() {
        timerAnimation = 0;
        frameAnimation = 0;
        timerAnimationMax = 9;
        isLive = true;
        status = LEFT;
        this.vel = 1.0 / 30;
        deltaSprite = 0.06d;
    }

    @Override
    public void loadImage() {
        leftSprite.add(Sprite.nuclear_left1.getFxImage());
        leftSprite.add(Sprite.nuclear_left2.getFxImage());
        leftSprite.add(Sprite.nuclear_left3.getFxImage());

        rightSprite.add(Sprite.nuclear_right1.getFxImage());
        rightSprite.add(Sprite.nuclear_right2.getFxImage());
        rightSprite.add(Sprite.nuclear_right3.getFxImage());

        upSprite = leftSprite;
        downSprite = rightSprite;
    }

    @Override
    public void die() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.nuclear_dead.getFxImage());
            case 1 -> setImage(Sprite.mob_dead1.getFxImage());
            case 2 -> setImage(Sprite.mob_dead2.getFxImage());
            case 3 -> {
                setImage(Sprite.mob_dead3.getFxImage());
                if (timerAnimation == timerAnimationMax) {
                    removeEntities.add(this);
                }
            }
        }
    }

    @Override
    public void update() {
        checkDie();
        if (isLive) {
            brain.autoBoom(this);
        }
        updateAnimation();
    }
}

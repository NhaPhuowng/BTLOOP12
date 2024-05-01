package bomberman.entities.mobs.enemies;

import bomberman.graphics.Sprite;

import static bomberman.control.Status.LEFT;

public class Doll extends Enemy {
    public Doll(double x, double y) {
        super(Sprite.doll_left1.getFxImage(), x, y);
        init();
    }

    private void init() {
        timerAnimation = 0;
        frameAnimation = 0;
        timerAnimationMax = 10;
        isLive = true;
        status = LEFT;
        this.vel = 0.04d;
        deltaSprite = 0.06d;
    }

    @Override
    public void loadImage() {
        leftSprite.add(Sprite.doll_left1.getFxImage());
        leftSprite.add(Sprite.doll_left2.getFxImage());
        leftSprite.add(Sprite.doll_left3.getFxImage());

        rightSprite.add(Sprite.doll_right1.getFxImage());
        rightSprite.add(Sprite.doll_right2.getFxImage());
        rightSprite.add(Sprite.doll_right3.getFxImage());

        upSprite = leftSprite;
        downSprite = rightSprite;
    }

    @Override
    public void die() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.doll_dead.getFxImage());
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
            brain.dollRandom(this);
        }
        updateAnimation();
    }
}

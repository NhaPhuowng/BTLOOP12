package bomberman.entities.mobs.enemies;

import bomberman.graphics.Sprite;

import static bomberman.control.Status.LEFT;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y) {
        super(Sprite.kondoria_left1.getFxImage(), x, y);
        init();
    }

    private void init() {
        timerAnimation = 0;
        frameAnimation = 0;
        timerAnimationMax = 8;
        isLive = true;
        status = LEFT;
        this.vel = 0.02d;
        deltaSprite = 0.035d;
    }

    @Override
    public void loadImage() {
        leftSprite.add(Sprite.kondoria_left1.getFxImage());
        leftSprite.add(Sprite.kondoria_left2.getFxImage());
        leftSprite.add(Sprite.kondoria_left3.getFxImage());

        rightSprite.add(Sprite.kondoria_right1.getFxImage());
        rightSprite.add(Sprite.kondoria_right2.getFxImage());
        rightSprite.add(Sprite.kondoria_right3.getFxImage());

        upSprite = leftSprite;
        downSprite = rightSprite;
    }

    @Override
    public void die() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.kondoria_dead.getFxImage());
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
            if (!brain.chase(this)) {
                brain.random(this);
            }
        }
        updateAnimation();
    }
}

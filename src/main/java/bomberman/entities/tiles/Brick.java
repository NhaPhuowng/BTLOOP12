package bomberman.entities.tiles;

import bomberman.graphics.Sprite;
import bomberman.utils.MapLoader;

public class Brick extends Tile {
    private boolean isBreak;

    public Brick(double x, double y) {
        super(Sprite.brick.getFxImage(), x, y);
        init();
    }

    private void init() {
        timerAnimation = 0;
        timerAnimationMax = 4;
        frameAnimation = 0;
        isBreak = false;
    }

    private void breakBrick() {
        switch (frameAnimation) {
            case 0 -> setImage(Sprite.brick_exploded.getFxImage());
            case 1 -> setImage(Sprite.brick_exploded1.getFxImage());
            case 2 -> {
                setImage(Sprite.brick_exploded2.getFxImage());
                if (timerAnimation == timerAnimationMax) {
                    removeEntities.add(this);
                    MapLoader.board[(int) y][(int) x] = 0;
                }
            }
        }
    }

    public void setBreak() {
        isBreak = true;
        timerAnimation = 0;
        frameAnimation = 0;
    }

    private void updateBreak() {
        if (isBreak) {
            breakBrick();
        }
    }

    @Override
    public void update() {
        updateBreak();
        updateAnimation();
    }
}

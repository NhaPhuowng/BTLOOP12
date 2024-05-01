package bomberman.entities.tiles;

import bomberman.graphics.Sprite;

public class Grass extends Tile {
    public Grass(double x, double y) {
        super(Sprite.grass.getFxImage(), x, y);
    }

    @Override
    public void update() {
    }
}

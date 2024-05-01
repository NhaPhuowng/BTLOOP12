package bomberman.entities.tiles;

import bomberman.graphics.Sprite;

public class Wall extends Tile {
    public Wall(double x, double y) {
        super(Sprite.wall.getFxImage(), x, y);
    }

    @Override
    public void update() {
    }
}

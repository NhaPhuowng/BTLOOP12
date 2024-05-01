package bomberman.entities.tiles;

import bomberman.entities.Entity;
import javafx.scene.image.Image;

public abstract class Tile extends Entity {
    public Tile(Image img, double x, double y) {
        super(img, x, y);
    }
}

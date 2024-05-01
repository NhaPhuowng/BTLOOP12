package bomberman.entities.items;

import bomberman.entities.Entity;
import bomberman.entities.mobs.player.Player;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Item extends Entity {
    public static List<Image> items = new ArrayList<>();

    public Item(Image img, double x, double y) {
        super(img, x, y);
    }

    /**
     * Buff cho nhân vật.
     */
    public abstract void buff(Player p);
}
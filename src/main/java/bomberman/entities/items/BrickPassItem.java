package bomberman.entities.items;

import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;

public class BrickPassItem  extends Item {
    public BrickPassItem(int x, int y) {
        super(Sprite.powerup_wallpass.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        items.add(img);
        p.setPassBrick(true);
        removeEntities.add(this);
    }
}
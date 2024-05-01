package bomberman.entities.items;

import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;

public class BombPassItem extends Item {
    public BombPassItem(double x, double y) {
        super(Sprite.powerup_bombpass.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        items.add(img);
        p.setPassBomb(true);
        removeEntities.add(this);
    }
}

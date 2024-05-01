package bomberman.entities.items;

import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int x, int y) {
        super(Sprite.powerup_speed.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        items.add(img);
        p.setVel(1.2 * p.getVel());
        removeEntities.add(this);
    }
}

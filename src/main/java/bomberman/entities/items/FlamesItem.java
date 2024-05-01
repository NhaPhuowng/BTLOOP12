package bomberman.entities.items;

import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;
import bomberman.entities.bomb.Flame;


public class FlamesItem extends Item {
    public FlamesItem(int x, int y) {
        super(Sprite.powerup_flames.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        items.add(img);
        Flame.flameSizeMax++;
        removeEntities.add(this);
    }
}

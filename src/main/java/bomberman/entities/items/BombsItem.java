package bomberman.entities.items;

import bomberman.entities.bomb.Bomb;
import bomberman.entities.mobs.Mob;
import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

public class BombsItem extends Item {
    public BombsItem(int x, int y) {
        super(Sprite.powerup_bombs.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        items.add(img);
        Bomb.maxBomb++;
        removeEntities.add(this);
    }
}

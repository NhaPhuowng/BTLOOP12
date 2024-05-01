package bomberman.entities.items;

import bomberman.Game;
import bomberman.Main;
import bomberman.control.Menu;
import bomberman.entities.mobs.enemies.Enemy;
import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;
import bomberman.utils.MapLoader;
import bomberman.utils.Sound;

public class Portal extends Item {
    public Portal(int x, int y) {
        super(Sprite.portal.getFxImage(), x, y);
    }

    @Override
    public void buff(Player p) {
        if (Enemy.numberOfEnemies() == 0) {
            Main.level++;
            if (Main.level > 7) {
                Menu.winGame();
            } else {
                new Sound("levelComplete");
                Game.resetGame();
                Menu.levelUpWaiting();
                new MapLoader(Main.level);
            }
        }
    }
}
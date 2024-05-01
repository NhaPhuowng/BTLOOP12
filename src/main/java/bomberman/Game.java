package bomberman;

import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.bomb.Flame;
import bomberman.entities.items.Item;
import bomberman.entities.mobs.enemies.Enemy;
import bomberman.entities.mobs.player.Player;
import bomberman.utils.Sound;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.util.Pair;

public class Game {
    public void addEvent(Scene scene, Player player) {
        scene.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                player.playerAct(keyEvent);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                player.changeKeepMove(keyEvent);
            }
        });
    }

    public void update() {
        for (int i = 0; i < Entity.entities.size(); i++) {
            Entity.entities.get(i).update();
        }
        Entity.removeEntities.forEach(e -> Entity.entities.remove(e));
        Entity.removeEntities.clear();
    }

    public void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Entity.entities.forEach(e -> e.render(gc));
    }

    public static void resetGame() {
        Flame.flameSizeMax = 1;
        Bomb.maxBomb = 1;
        Bomb.puttedBomb = 0;
        Player.pos = new Pair<>(100, 100);
        Entity.entities.clear();
        Entity.removeEntities.clear();
        Item.items.clear();
    }
}

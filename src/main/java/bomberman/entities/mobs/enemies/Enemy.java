package bomberman.entities.mobs.enemies;

import bomberman.control.Collision;
import bomberman.entities.Entity;
import bomberman.entities.mobs.Mob;
import javafx.scene.image.Image;

import static bomberman.control.Status.DIE;

public abstract class Enemy extends Mob {
    protected AI brain = new AI();

    public Enemy(Image img, double x, double y) {
        super(img, x, y);
    }

    @Override
    public void checkDie() {
        if (Collision.collideWithFlame(this)) {
            isLive = false;
        }
        if (!isLive) {
            if (status != DIE) {
                this.frameAnimation = 0;
                this.frameAnimationMax = 4;
                this.timerAnimation = 0;
                this.timerAnimationMax = 12;
            }
            status = DIE;
            die();
        }
    }

    /**
     * Số lượng enemy còn lại trên map.
     */
    public static int numberOfEnemies() {
        int cnt = 0;
        for (Entity entity : entities) {
            if (entity instanceof Enemy) {
                cnt++;
            }
        }
        return cnt;
    }
}

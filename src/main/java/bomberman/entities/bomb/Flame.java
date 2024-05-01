package bomberman.entities.bomb;

import bomberman.control.Collision;
import bomberman.entities.Entity;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static bomberman.graphics.Sprite.SCALED_SIZE;

public class Flame extends Entity {
    public static int flameSizeMax = 1;
    public int flameSize;
    private boolean isExploded;
    private Image midFlame;
    private final List<Flame> hFlame = new ArrayList<>();
    private final List<Flame> vFlame = new ArrayList<>();

    public Flame(double x, double y) {
        super(x, y);
        init();
    }

    public Flame(Image img, double x, double y) {
        this.midFlame = img;
        this.x = x;
        this.y = y;
    }

    private void init() {
        timerAnimationMax = 4;
        isExploded = true;
        flameSize = flameSizeMax;
    }

    public List<Flame> gethFlame() {
        return hFlame;
    }

    public List<Flame> getvFlame() {
        return vFlame;
    }

    public void flame() {
        midFlame = Sprite.bomb_exploded2.getFxImage();

        //horizontal flame
        for (int i = 1; i < flameSize; i++) {
            hFlame.add(0, new Flame(Sprite.explosion_horizontal2.getFxImage(), x - i, y));
            hFlame.add(new Flame(Sprite.explosion_horizontal2.getFxImage(), x + i, y));
        }
        hFlame.add(0, new Flame(Sprite.explosion_horizontal_left_last2.getFxImage(), x - flameSize, y));
        hFlame.add(new Flame(Sprite.explosion_horizontal_right_last2.getFxImage(), x + flameSize, y));

        //vertical flame
        for (int i = 1; i < flameSize; i++) {
            vFlame.add(0, new Flame(Sprite.explosion_vertical2.getFxImage(), x, y - i));
            vFlame.add(new Flame(Sprite.explosion_vertical2.getFxImage(), x, y + i));
        }
        vFlame.add(0, new Flame(Sprite.explosion_vertical_top_last2.getFxImage(), x, y - flameSize));
        vFlame.add(new Flame(Sprite.explosion_vertical_down_last2.getFxImage(), x, y + flameSize));
    }

    public void flame1() {
        midFlame = Sprite.bomb_exploded1.getFxImage();

        //horizontal flame
        for (int i = 1; i < flameSize; i++) {
            hFlame.add(0, new Flame(Sprite.explosion_horizontal1.getFxImage(), x - i, y));
            hFlame.add(new Flame(Sprite.explosion_horizontal1.getFxImage(), x + i, y));
        }
        hFlame.add(0, new Flame(Sprite.explosion_horizontal_left_last1.getFxImage(), x - flameSize, y));
        hFlame.add(new Flame(Sprite.explosion_horizontal_right_last1.getFxImage(), x + flameSize, y));
        //vertical flame
        for (int i = 1; i < flameSize; i++) {
            vFlame.add(0, new Flame(Sprite.explosion_vertical1.getFxImage(), x, y - i));
            vFlame.add(new Flame(Sprite.explosion_vertical1.getFxImage(), x, y + i));
        }
        vFlame.add(0, new Flame(Sprite.explosion_vertical_top_last1.getFxImage(), x, y - flameSize));
        vFlame.add(new Flame(Sprite.explosion_vertical_down_last1.getFxImage(), x, y + flameSize));
    }

    public void flame2() {
        midFlame = Sprite.bomb_exploded.getFxImage();

        //horizontal flame
        for (int i = 1; i < flameSize; i++) {
            hFlame.add(0, new Flame(Sprite.explosion_horizontal.getFxImage(), x - i, y));
            hFlame.add(new Flame(Sprite.explosion_horizontal.getFxImage(), x + i, y));
        }
        hFlame.add(0, new Flame(Sprite.explosion_horizontal_left_last.getFxImage(), x - flameSize, y));
        hFlame.add(new Flame(Sprite.explosion_horizontal_right_last.getFxImage(), x + flameSize, y));

        //vertical flame
        for (int i = 1; i < flameSize; i++) {
            vFlame.add(0, new Flame(Sprite.explosion_vertical.getFxImage(), x, y - i));
            vFlame.add(new Flame(Sprite.explosion_vertical.getFxImage(), x, y + i));
        }
        vFlame.add(0, new Flame(Sprite.explosion_vertical_top_last.getFxImage(), x, y - flameSize));
        vFlame.add(new Flame(Sprite.explosion_vertical_down_last.getFxImage(), x, y + flameSize));
    }

    private void explode() {
        switch (frameAnimation) {
            case 0 -> flame();
            case 1 -> flame1();
            case 2 -> flame2();
        }
    }

    /**
     * Khi flame va chạm tường hoặc gạch thì dừng lại.
     */
    private void removeBlockedFlame(List<Flame> list) {
        // Down, right side
        for (int i = flameSize; i < 2 * flameSize; i++) {
            if (Collision.collideWithWall(list.get(i)) || Collision.collideWithBrick(list.get(i))) {
                list.subList(i, 2 * flameSize).clear();
                break;
            }
        }
        // Top, left side
        for (int i = flameSize - 1; i >= 0; i--) {
            if (Collision.collideWithWall(list.get(i)) || Collision.collideWithBrick(list.get(i))) {
                list.subList(0, i + 1).clear();
                break;
            }
        }
    }

    private void flameBlocked() {
        removeBlockedFlame(vFlame);
        removeBlockedFlame(hFlame);
    }

    @Override
    public void update() {
        if (timerAnimation == 0) {
            hFlame.clear();
            vFlame.clear();
            explode();
            flameBlocked();
            if (frameAnimation == 0) {
                isExploded = !isExploded;
                if (isExploded) {
                    removeEntities.add(this);
                }
            }
        }
        updateAnimation();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(midFlame, x * SCALED_SIZE, y * SCALED_SIZE);
        hFlame.forEach(h -> gc.drawImage(h.midFlame, h.x * SCALED_SIZE, h.y * SCALED_SIZE));
        vFlame.forEach(v -> gc.drawImage(v.midFlame, v.x * SCALED_SIZE, v.y * SCALED_SIZE));
    }
}

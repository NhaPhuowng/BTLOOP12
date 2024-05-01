package bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

import static bomberman.graphics.Sprite.*;

public abstract class Entity {
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> removeEntities = new ArrayList<>();
    protected int timerAnimationMax = 25;
    protected int timerAnimation = 0;
    protected int frameAnimationMax = 3;
    protected int frameAnimation = 0;
    protected double x;
    protected double y;
    protected double deltaSprite = 0;
    protected Image img;

    public Entity() {
    }

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        entities.add(this);
    }

    public Entity(Image img, double x, double y) {
        this.img = img;
        this.x = x;
        this.y = y;
        entities.add(this);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getRectX() {
        return x + deltaSprite / 2;
    }

    /**
     * set x theo rectX.
     */
    public void setPosX(double x) {
        this.x = x - deltaSprite / 2;
    }

    public double getRectY() {
        return y + deltaSprite;
    }

    /**
     * set y entity theo rectY.
     */
    public void setPosY(double y) {
        this.y = y - deltaSprite;
    }

    public double getRectSize() {
        return 1.0d - deltaSprite;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public Image getImage() {
        return img;
    }

    public void setFrameAnimation(int frameAnimation) {
        this.frameAnimation = frameAnimation;
    }

    public int getFrameAnimation() {
        return frameAnimation;
    }

    public void updateAnimation() {
        timerAnimation++;
        if (timerAnimation > timerAnimationMax) {
            frameAnimation++;
            timerAnimation = 0;
        }
        frameAnimation %= frameAnimationMax;
    }

    public void update() {
        updateAnimation();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * SCALED_SIZE, y * SCALED_SIZE);
    }
}

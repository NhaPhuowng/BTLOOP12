package bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

import static bomberman.graphics.Sprite.*;

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;

    public Entity() {}

    public Entity(Image img, int x, int y) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public Image getImage() {
        return img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x * SCALED_SIZE, y * SCALED_SIZE);
    }
}

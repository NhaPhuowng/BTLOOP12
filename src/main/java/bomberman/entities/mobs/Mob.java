package bomberman.entities.mobs;

import bomberman.control.Status;
import bomberman.entities.Entity;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {
    protected boolean isLive;
    protected Status status;
    protected double vel;
    protected List<Image> leftSprite = new ArrayList<>();
    protected List<Image> rightSprite = new ArrayList<>();
    protected List<Image> upSprite = new ArrayList<>();
    protected List<Image> downSprite = new ArrayList<>();

    public Mob(Image img, double x, double y) {
        super(img, x, y);
        loadImage();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setVel(double vel) {
        this.vel = vel;
    }

    public double getVel() {
        return vel;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public List<Image> getLeftSprite() {
        return leftSprite;
    }

    public List<Image> getRightSprite() {
        return rightSprite;
    }

    public List<Image> getUpSprite() {
        return upSprite;
    }

    public List<Image> getDownSprite() {
        return downSprite;
    }

    public abstract void loadImage();

    /**
     * Kiểm tra mob có va chạm với nguy hiểm không.
     */
    public abstract void checkDie();

    public abstract void die();
}

package bomberman.entities.mobs;

import bomberman.control.Movement;
import bomberman.entities.Entity;
import javafx.scene.image.Image;
import bomberman.control.Movement.*;

public abstract class Mob extends Entity {
    protected  boolean isMove;
    protected boolean isLive;
    protected Direction dir;
    protected double vel;

    public Mob() {}

    public void setIsMove(boolean isMove) {
        this.isMove = isMove;
    }

    public boolean getIsMove() {
        return isMove;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    public boolean getIsLive() {
        return isLive;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public void setVel(double vel) {
        this.vel = vel;
    }

    public double getVel() {
        return vel;
    }
}

package bomberman.entities.mobs.character;

import bomberman.entities.mobs.Mob;
import javafx.scene.input.KeyEvent;

import static bomberman.control.Movement.*;
import static bomberman.entities.bomb.Bomb.*;

public class Character extends Mob {
    public boolean canPutBomb;

    public Character() {
        init();
    }

    public void init() {
        isMove = false;
        isLive = true;
        vel = 1.0d;
        canPutBomb = true;
    }

    public void characterAct(KeyEvent e) {
        switch (e.getCode()) {
            case W -> moveUp(this);
            case A -> moveLeft(this);
            case S -> moveDown(this);
            case D -> moveRight(this);
            case SPACE -> putBomb(canPutBomb);
        }
    }

    public void update() {

    }
}

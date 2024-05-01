package bomberman.entities.bomb;

import bomberman.entities.Entity;

public class Bomb extends Entity {

    public static void putBomb(boolean canPutBomb) {
        if (!canPutBomb) { return; }
    }
}

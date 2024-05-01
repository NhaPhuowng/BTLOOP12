package bomberman.control;

import bomberman.entities.mobs.Mob;

import static bomberman.control.Collision.*;

public class Movement {
    /**
     * Kiểm tra xem liệu mob có thể đi tiếp không.
     */
    public static boolean canMove(Mob mob, Status status) {
        switch (status) {
            case UP -> {
                return Collision.up(mob) == mob.getRectY() - mob.getVel();
            }
            case LEFT -> {
                return Collision.left(mob) == mob.getRectX() - mob.getVel();
            }
            case DOWN -> {
                return Collision.down(mob) == mob.getRectY() + mob.getVel();
            }
            default -> {
                return Collision.right(mob) == mob.getRectX() + mob.getVel();
            }
        }
    }

    public static void moveUp(Mob mob) {
        mob.setStatus(Status.UP);
        mob.setPosY(up(mob));
        mob.setImage(mob.getUpSprite().get(mob.getFrameAnimation()));
    }

    public static void moveLeft(Mob mob) {
        mob.setStatus(Status.LEFT);
        mob.setPosX(left(mob));
        mob.setImage(mob.getLeftSprite().get(mob.getFrameAnimation()));
    }

    public static void moveDown(Mob mob) {
        mob.setStatus(Status.DOWN);
        mob.setPosY(down(mob));
        mob.setImage(mob.getDownSprite().get(mob.getFrameAnimation()));
    }

    public static void moveRight(Mob mob) {
        mob.setStatus(Status.RIGHT);
        mob.setPosX(right(mob));
        mob.setImage(mob.getRightSprite().get(mob.getFrameAnimation()));
    }
}

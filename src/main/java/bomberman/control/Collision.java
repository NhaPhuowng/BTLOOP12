package bomberman.control;

import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.bomb.Flame;
import bomberman.entities.items.Item;
import bomberman.entities.mobs.Mob;
import bomberman.entities.mobs.enemies.Enemy;
import bomberman.entities.mobs.enemies.Kondoria;
import bomberman.entities.mobs.enemies.Minvo;
import bomberman.entities.mobs.player.Player;
import bomberman.entities.tiles.Brick;
import bomberman.entities.tiles.Wall;

public class Collision {
    /*************************************** can move ***************************************/
    private static boolean collide(double x1, double y1, double size1,
                                   double x2, double y2, double size2) {
        // Round double number to compare
        x1 = Math.round(x1 * 10000) / 10000.0;
        y1 = Math.round(y1 * 10000) / 10000.0;
        size1 = Math.round(size1 * 10000) / 10000.0;
        x2 = Math.round(x2 * 10000) / 10000.0;
        y2 = Math.round(y2 * 10000) / 10000.0;
        size2 = Math.round(size2 * 10000) / 10000.0;

        return x1 < x2 + size2
                && x1 + size1 > x2
                && y1 < y2 + size2
                && y1 + size1 > y2;
    }

    public static boolean specialCase(Entity block, Mob mob) {
        return (block instanceof Bomb && ((Bomb) block).isPassable() && mob instanceof Player)
                || (block instanceof Brick && mob instanceof Player && ((Player) mob).isPassBrick())
                || (block instanceof Brick && mob instanceof Kondoria)
                || (block instanceof Brick && mob instanceof Minvo);
    }

    public static double left(Mob mob) {
        for (Entity block : Entity.entities) {
            if (block instanceof Wall || block instanceof Brick || block instanceof Bomb) {
                if (collide(mob.getRectX() - mob.getVel(), mob.getRectY(), mob.getRectSize(),
                        block.getX(), block.getY(), block.getRectSize())) {
                    if (specialCase(block, mob)) {
                        continue;
                    }
                    return block.getRectX() + block.getRectSize();
                }
            }
        }
        return mob.getRectX() - mob.getVel();
    }

    public static double right(Mob mob) {
        for (Entity block : Entity.entities) {
            if (block instanceof Wall || block instanceof Brick || block instanceof Bomb) {
                if (collide(mob.getRectX() + mob.getVel(), mob.getRectY(), mob.getRectSize(),
                        block.getRectX(), block.getRectY(), block.getRectSize())) {
                    if (specialCase(block, mob)) {
                        continue;
                    }
                    return block.getRectX() - mob.getRectSize();
                }
            }
        }
        return mob.getRectX() + mob.getVel();
    }

    public static double up(Mob mob) {
        for (Entity block : Entity.entities) {
            if (block instanceof Wall || block instanceof Brick || block instanceof Bomb) {
                if (collide(mob.getRectX(), mob.getRectY() - mob.getVel(), mob.getRectSize(),
                        block.getRectX(), block.getRectY(), block.getRectSize())) {
                    if (specialCase(block, mob)) {
                        continue;
                    }
                    return block.getRectY() + block.getRectSize();
                }
            }
        }
        return mob.getRectY() - mob.getVel();
    }

    public static double down(Mob mob) {
        for (Entity block : Entity.entities) {
            if (block instanceof Wall || block instanceof Brick || block instanceof Bomb) {
                if (collide(mob.getRectX(), mob.getRectY() + mob.getVel(), mob.getRectSize(),
                        block.getRectX(), block.getRectY(), block.getRectSize())) {
                    if (specialCase(block, mob)) {
                        continue;
                    }
                    return block.getRectY() - mob.getRectSize();
                }
            }
        }
        return mob.getRectY() + mob.getVel();
    }

    /*************************************** collide with object ***************************************/

    public static boolean collideWithWall(Entity entity) {
        for (Entity block : Entity.entities) {
            if (block instanceof Wall) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        block.getRectX(), block.getRectY(), block.getRectSize())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collideWithBrick(Entity entity) {
        for (Entity block : Entity.entities) {
            if (block instanceof Brick) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        block.getRectX(), block.getRectY(), block.getRectSize())) {
                    if (entity instanceof Flame) {
                        ((Brick) block).setBreak();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean collideWithFlame(Entity entity) {
        for (Entity flame : Entity.entities) {
            if (flame instanceof Flame) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        flame.getRectX(), flame.getRectY(), flame.getRectSize())) {
                    return true;
                }
                // Collide with horizontal flames
                for (Entity hFlame : (((Flame) flame).gethFlame())) {
                    if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                            hFlame.getRectX(), hFlame.getRectY(), hFlame.getRectSize())) {
                        return true;
                    }
                }
                // Collide with vertical flames
                for (Entity vFlame : (((Flame) flame).getvFlame())) {
                    if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                            vFlame.getRectX(), vFlame.getRectY(), vFlame.getRectSize())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Bomb collideWithBomb(Entity entity) {
        for (Entity bomb : Entity.entities) {
            if (bomb instanceof Bomb) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        bomb.getRectX(), bomb.getRectY(), bomb.getRectSize())) {
                    return (Bomb) bomb;
                }
            }
        }
        return null;
    }

    public static boolean collideWithEnemy(Entity entity) {
        for (Entity enemy : Entity.entities) {
            if (enemy instanceof Enemy) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        enemy.getRectX(), enemy.getRectY(), enemy.getRectSize())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Item collideWithItem(Entity entity) {
        for (Entity item : Entity.entities) {
            if (item instanceof Item) {
                if (collide(entity.getRectX(), entity.getRectY(), entity.getRectSize(),
                        item.getRectX(), item.getRectY(), item.getRectSize())) {
                    return (Item) item;
                }
            }
        }
        return null;
    }
}

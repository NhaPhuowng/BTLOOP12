package bomberman.entities.mobs.enemies;

import bomberman.control.Status;
import bomberman.entities.bomb.Flame;
import bomberman.entities.mobs.Mob;
import bomberman.entities.mobs.player.Player;
import javafx.util.Pair;

import java.util.*;

import static bomberman.control.Movement.*;
import static bomberman.utils.MapLoader.*;

public class AI {
    private int step;
    private int timeStep;
    private int timer;
    private boolean activeBoom;
    private final int waitToBoom = 35;

    public AI() {
        init();
    }

    private void init() {
        step = 0;
        timeStep = 0;
        timer = 0;
    }

    /**
     * Di chuyển của enemy.
     */
    public void enemyMove(Mob mob) {
        switch (mob.getStatus()) {
            case UP -> {
                moveUp(mob);
            }
            case LEFT -> {
                moveLeft(mob);
            }
            case DOWN -> {
                moveDown(mob);
            }
            case RIGHT -> {
                moveRight(mob);
            }
        }
    }

    /**
     * Random hướng đi và số step.
     */
    public void random(Mob mob) {
        if (timeStep == 0) {
            Random rand = new Random();
            step = rand.nextInt(3) + 2;
            if (mob instanceof Nuclear) {
                int velType = rand.nextInt(3);
                switch (velType) {
                    case 0 -> {
                        mob.setVel(1.0 / 30);
                    }
                    case 1 -> {
                        mob.setVel(1.0 / 24);
                    }
                    case 2 -> {
                        mob.setVel(1.0 / 20);
                    }
                }
            }
            timeStep = (int) (step / mob.getVel());
            switch (rand.nextInt(4)) {
                case 0 -> mob.setStatus(Status.UP);
                case 1 -> mob.setStatus(Status.LEFT);
                case 2 -> mob.setStatus(Status.DOWN);
                default -> mob.setStatus(Status.RIGHT);
            }
        }
        timeStep--;
        if (!canMove(mob, mob.getStatus())) {
            timeStep = 0;
        } else {
            enemyMove(mob);
        }
    }

    /**
     * Random hướng đi nhưng ưu tiên sang ngang hơn.
     */
    public void dollRandom(Mob mob) {
        if (timeStep == 0) {
            Random rand = new Random();
            step = rand.nextInt(4) + 2;
            timeStep = (int) (step / mob.getVel());
            switch (rand.nextInt(8)) {
                case 0, 1, 2 -> mob.setStatus(Status.LEFT);
                case 3, 4, 5 -> mob.setStatus(Status.RIGHT);
                case 6 -> mob.setStatus(Status.DOWN);
                default -> mob.setStatus(Status.UP);
            }
        }
        timeStep--;
        if (!canMove(mob, mob.getStatus())) {
            timeStep = 0;
        } else {
            enemyMove(mob);
        }
    }

    /**
     * Tìm đường và đuổi theo player.
     */
    public boolean chase(Mob mob) {
        Pair<Integer, Integer> mobPos = new Pair<>((int) Math.round(mob.getRectY()),
                (int) Math.round(mob.getRectX()));
        Pair<Integer, Integer> playerPos = Player.pos;
        if (mobPos.equals(playerPos)) {
            return true;
        }
        boolean[][] visited = new boolean[height][width];
        final int[] moveX = {1, -1, 0, 0};
        final int[] moveY = {0, 0, 1, -1};
        int[][] minStep = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                minStep[i][j] = 10000;
            }
        }
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(mobPos);
        visited[mobPos.getKey()][mobPos.getValue()] = true;
        minStep[mobPos.getKey()][mobPos.getValue()] = 0;

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int x = queue.peek().getKey();
            int y = queue.peek().getValue();
            queue.remove();
            for (int i = 0; i < 4; i++) {
                int u = x + moveX[i];
                int v = y + moveY[i];
                if (!inBoard(u, v)) {
                    continue;
                }
                boolean canPass;
                if (mob instanceof Kondoria || mob instanceof Minvo) {
                    canPass = (board[u][v] != 2);
                } else {
                    canPass = (board[u][v] != 2 && board[u][v] != 3);
                }
                if (canPass && !visited[u][v]) {
                    queue.add(new Pair<>(u, v));
                    visited[u][v] = true;
                    minStep[u][v] = minStep[x][y] + 1;
                }
                if (u == playerPos.getKey() && v == playerPos.getValue()) {
                    minStep[u][v] = minStep[x][y] + 1;
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            return false;
        }

        int x = playerPos.getKey();
        int y = playerPos.getValue();
        if (mob instanceof Oneal && minStep[x][y] > 8) {
            return false;
        }
        if (mob instanceof Kondoria && minStep[x][y] > 15) {
            return false;
        }
        if (mob instanceof Minvo && minStep[x][y] > 20) {
            return false;
        }

        Stack<Status> stack = new Stack<>();
        while (x != mobPos.getKey() || y != mobPos.getValue()) {
            for (int i = 0; i < 4; i++) {
                int u = x + moveX[i];
                int v = y + moveY[i];
                if (!inBoard(u, v)) {
                    continue;
                }
                if (minStep[u][v] + 1 == minStep[x][y]) {
                    x = u;
                    y = v;
                    switch (i) {
                        case 0 -> stack.push(Status.UP);
                        case 1 -> stack.push(Status.DOWN);
                        case 2 -> stack.push(Status.LEFT);
                        case 3 -> stack.push(Status.RIGHT);
                    }
                }
            }
        }

        if (timeStep == 0) {
            mob.setStatus(stack.peek());
            step = 1;
            timeStep = (int) (step / mob.getVel());
        }
        timeStep--;
        enemyMove(mob);
        return true;
    }

    /**
     * Tự động nổ khi player ở gần.
     */
    public void autoBoom(Mob mob) {
        Pair<Integer, Integer> playerPos = Player.pos;
        Pair<Integer, Integer> mobPos = new Pair<>((int) Math.round(mob.getRectY()),
                (int) Math.round(mob.getRectX()));

        final int[] moveX = {1, -1, 0, 0, 2, -2, 0, 0, 3, -3, 0, 0};
        final int[] moveY = {0, 0, 1, -1, 0, 0, 2, -2, 0, 0, 3, -3};

        if (!activeBoom) {
            for (int i = 0; i < 12; i++) {
                int u = mobPos.getValue() + moveX[i];
                int v = mobPos.getKey() + moveY[i];
                if (u == playerPos.getValue() && v == playerPos.getKey()) {
                    activeBoom = true;
                    break;
                }
            }
        }

        if (activeBoom) {
            timer++;
            if (timer >= waitToBoom) {
                int tmp = Flame.flameSizeMax;
                Flame.flameSizeMax = 2;
                new Flame(mobPos.getValue(), mobPos.getKey());
                Flame.flameSizeMax = tmp;
                timer = 0;
                activeBoom = false;
            }
        } else {
            random(mob);
        }
    }
}

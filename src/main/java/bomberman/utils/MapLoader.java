package bomberman.utils;

import bomberman.Main;
import bomberman.entities.items.*;
import bomberman.entities.mobs.Mob;
import bomberman.entities.mobs.enemies.*;
import bomberman.entities.mobs.player.Player;
import bomberman.entities.tiles.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import static bomberman.entities.Entity.entities;
import static bomberman.Main.player;

public class MapLoader {
    public static int height;
    public static int width;
    public static int[][] board;

    public MapLoader(int level) {
        String path = "src/main/resources/levels/Level" + level + ".txt";

        final File fileName = new File(path);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner scan = new Scanner(inputFile);
            String line = scan.nextLine();

            StringTokenizer tokens = new StringTokenizer(line);

            Main.level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());

            board = new int[height][width];

            // Make a background with grass
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    new Grass(j, i);
                }
            }

            while (scan.hasNextLine()) {
                for (int i = 0; i < height; i++) {
                    String lineTile = scan.nextLine();
                    lineTile = lineTile.replaceAll(" ", "");
                    char[] lineChar = lineTile.toCharArray();
//                    StringTokenizer tokenTile = new StringTokenizer(lineTile);
                    for (int j = 0; j < width; j++) {
                        char token = lineChar[j];
                        switch (token) {
                            case '1' -> {
                                new Portal(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '2' -> {
                                new Wall(j, i);
                                board[i][j] = 2;
                            }
                            case '3' -> {
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '4' -> {
                                new SpeedItem(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '5' -> {
                                new FlamesItem(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '6' -> {
                                new BombsItem(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '7' -> {
                                new BrickPassItem(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case '8' -> {
                                new BombPassItem(j, i);
                                new Brick(j, i);
                                board[i][j] = 3;
                            }
                            case 'B' -> {
                                new Balloom(j, i);
                            }
                            case 'O' -> {
                                new Oneal(j, i);
                            }
                            case 'D' -> {
                                new Doll(j, i);
                            }
                            case 'K' -> {
                                new Kondoria(j, i);
                            }
                            case 'N' -> {
                                new Nuclear(j, i);
                            }
                            case 'M' -> {
                                new Minvo(j, i);
                            }
                            case '!' -> {
                                player = new Player(j, i);
                            }
                        }
                    }
                }
            }
            // Make enemies are front of blocks
            for (int i = entities.size() - 1; i >= 0; i--) {
                if (entities.get(i) instanceof Mob) {
                    entities.add(entities.get(i));
                    entities.remove(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean inBoard(int x, int y) {
        return x < height && x >= 0 && y < width && y >= 0;
    }
}

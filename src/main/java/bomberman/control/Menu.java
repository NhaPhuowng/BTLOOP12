package bomberman.control;

import bomberman.Game;
import bomberman.utils.MapLoader;
import bomberman.utils.Sound;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.Duration;
import java.time.LocalDateTime;

import static bomberman.Main.*;
import static bomberman.entities.bomb.Bomb.maxBomb;
import static bomberman.entities.bomb.Bomb.puttedBomb;
import static bomberman.entities.items.Item.items;
import static bomberman.utils.Sound.menuClip;
import static bomberman.utils.Sound.themeClip;

public class Menu {
    public static Text levelMenu, bomb, time;
    public static ImageView statusGame, newGameButton, exitButton;
    public static Image resumeGame, pauseGame;
    public static ImageView backgroundMenu, levelUpBackground, winGameBackground;
    public static final int timeMax = 180;
    public static int time_number = timeMax;
    public static Pane itemsPane;
    public static Pane pane;
    static Image levelUpImage = new Image("file:src/main/resources/levels/level_up.png");
    static Image newGameImage = new Image("file:src/main/resources/levels/start.png");
    static Image exitGameImage = new Image("file:src/main/resources/levels/exit.png");
    static Image backgroundMenuImage = new Image("file:src/main/resources/levels/menu.gif");
    static Image winImage = new Image("file:src/main/resources/levels/winGame.png");
    static AnimationTimer timerLevelUp, timerGameOver, timerWingame;

    public static void createMenu(Group root) {
        new Sound("menu");
        winGameBackground = new ImageView(winImage);
        levelUpBackground = new ImageView(levelUpImage);
        root.getChildren().add(winGameBackground);
        root.getChildren().add(levelUpBackground);
        levelUpBackground.setVisible(false);
        winGameBackground.setVisible(false);
        if (!isRunning) {
            backgroundMenu = new ImageView(backgroundMenuImage);
            newGameButton = new ImageView(newGameImage);
            exitButton = new ImageView(exitGameImage);

            newGameButton.setX(30);
            newGameButton.setY(180);
            newGameButton.setScaleX(0.5);
            newGameButton.setScaleY(0.5);

            exitButton.setX(30);
            exitButton.setY(280);
            exitButton.setScaleX(0.5);
            exitButton.setScaleY(0.5);


            root.getChildren().add(backgroundMenu);
            root.getChildren().add(newGameButton);
            root.getChildren().add(exitButton);
        }

        levelMenu = new Text("Level: " + level);
        levelMenu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        levelMenu.setFill(Color.WHITE);
        levelMenu.setX(530);
        levelMenu.setY(20);

        bomb = new Text("Bombs: " + (maxBomb - puttedBomb));
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(610);
        bomb.setY(20);

        time = new Text("Times: " + time_number);
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(700);
        time.setY(20);

        resumeGame = new Image("file:src/main/resources/levels/pause.png");
        pauseGame = new Image("file:src/main/resources/levels/resume.png");
        statusGame = new ImageView(resumeGame);
        statusGame.setX(5);
        statusGame.setY(3);

        itemsPane = new Pane();
        itemsPane.setLayoutX(80);

        pane = new Pane();
        pane.getChildren().addAll(itemsPane, levelMenu, bomb, time, statusGame);
        pane.setTranslateY(480);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #405066");


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                newGameButton.setOnMouseClicked(event -> {
                    new Sound("clickMenuButton");
                    menuClip.stop();
                    new Sound("theme");
                    isRunning = true;
                    backgroundMenu.setVisible(false);
                    newGameButton.setVisible(false);
                    exitButton.setVisible(false);
                    root.getChildren().add(pane);
                });
                exitButton.setOnMouseClicked(event -> {
                    new Sound("clickMenuButton");
                    Platform.exit();
                });
                statusGame.setOnMouseClicked(event -> {
                    new Sound("clickPauseButton");
                    isPause = !isPause;
                    isRunning = !isRunning;
                });
                updateMenu();
            }
        };
        timer.start();
    }

    public static void gameOver(Group root) {
        new Sound("gameOver");
        Image gameOverBackground = new Image("file:src/main/resources/levels/game_over.jpg");
        backgroundMenu.setImage(gameOverBackground);
        backgroundMenu.setVisible(true);
        newGameButton.setVisible(true);
        exitButton.setVisible(true);
        pane.setVisible(false);
        themeClip.stop();

        newGameButton.setX(230);
        newGameButton.setY(330);
        newGameButton.setScaleX(0.4);
        newGameButton.setScaleY(0.4);

        exitButton.setX(230);
        exitButton.setY(390);
        exitButton.setScaleX(0.4);
        exitButton.setScaleY(0.4);
        isRunning = false;
        timerGameOver = new AnimationTimer() {
            @Override
            public void handle(long l) {
                newGameButton.setOnMouseClicked(event -> {
                    new Sound("clickGameOver");
                    isRunning = true;
                    Game.resetGame();
                    themeClip.setMicrosecondPosition(0);
                    themeClip.start();
                    backgroundMenu.setVisible(false);
                    newGameButton.setVisible(false);
                    exitButton.setVisible(false);
                    pane.setVisible(true);
                    time_number = timeMax;
                    new MapLoader(level);
                    timerGameOver.stop();
                });
                exitButton.setOnMouseClicked(event -> {
                    new Sound("clickGameOver");
                    Platform.exit();
                });
            }
        };
        timerGameOver.start();
    }

    public static void updateMenu() {
        levelMenu.setText("Level: " + level);
        bomb.setText("Bombs: " + (maxBomb - puttedBomb));
        itemsPane.getChildren().clear();
        for (int i = 0; i < items.size(); i++) {
            ImageView itemImgView = new ImageView(items.get(i));
            itemImgView.setLayoutX(35 * i);
            itemsPane.getChildren().add(itemImgView);
        }
        if (isPause) {
            statusGame.setImage(pauseGame);
        } else {
            statusGame.setImage(resumeGame);
        }
    }

    public static void levelUpWaiting() {
        levelUpBackground.setVisible(true);
        themeClip.stop();
        themeClip.setMicrosecondPosition(0);
        isRunning = false;
        time_number = timeMax;
        timerLevelUp = new AnimationTimer() {
            private LocalDateTime start = LocalDateTime.now();

            @Override
            public void handle(long l) {
                LocalDateTime now = LocalDateTime.now();
                if (Duration.between(start, now).toMillis() >= 3000) {
                    levelUpBackground.setVisible(false);
                    isRunning = true;
                    themeClip.start();
                    timerLevelUp.stop();
                }
            }
        };
        timerLevelUp.start();
    }

    public static void winGame() {
        themeClip.stop();
        new Sound("winner");
        isRunning = false;
        winGameBackground.setVisible(true);
        newGameButton.setX(460);
        exitButton.setX(460);

        newGameButton.setVisible(true);
        exitButton.setVisible(true);
        pane.setVisible(false);
        timerWingame = new AnimationTimer() {
            @Override
            public void handle(long l) {
                newGameButton.setOnMouseClicked(event -> {
                    new Sound("clickMenuButton");
                    isRunning = true;
                    Game.resetGame();
                    themeClip.setMicrosecondPosition(0);
                    themeClip.start();
                    backgroundMenu.setVisible(false);
                    newGameButton.setVisible(false);
                    exitButton.setVisible(false);
                    winGameBackground.setVisible(false);
                    pane.setVisible(true);
                    time_number = timeMax;
                    new MapLoader(1);
                    timerWingame.stop();
                });
                exitButton.setOnMouseClicked(event -> {
                    new Sound("clickMenuButton");
                    Platform.exit();
                });
            }
        };
        timerWingame.start();
    }
}

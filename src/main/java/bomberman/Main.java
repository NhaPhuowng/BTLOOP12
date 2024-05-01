package bomberman;

import bomberman.entities.mobs.player.Player;
import bomberman.graphics.Sprite;
import bomberman.utils.MapLoader;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import static bomberman.control.Menu.*;
import static bomberman.control.Menu.time_number;

public class Main extends Application {
    private Canvas canvas;
    public static boolean isRunning = false;
    public static boolean isPause = false;
    private static long last_time;
    public static int level = 1;
    private GraphicsContext gc;
    MapLoader map = new MapLoader(1);
    public static Player player;

    public void start(Stage stage) throws IOException {
        canvas = new Canvas(Sprite.SCALED_SIZE * MapLoader.width,
                Sprite.SCALED_SIZE * MapLoader.height);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        createMenu(root);
        Scene scene = new Scene(root);
        Game game = new Game();

        AnimationTimer timer = new AnimationTimer() {
            private LocalDateTime start = LocalDateTime.now();
            private LocalDateTime stop = LocalDateTime.now();

            @Override
            public void handle(long l) {
                if (isRunning) {
                    stop = LocalDateTime.now();
                    long deltaTime = Duration.between(start, stop).toMillis();
                    if (deltaTime > 15) {
                        start = LocalDateTime.now();
                        game.addEvent(scene, player);
                        game.update();
                    }
                    game.render(gc, canvas);
                    if (player.checkDieCompleted()) {
                        gameOver(root);
                    }
                    if (!isPause) {
                        timeCount(root);
                    }
                }
            }
        };
        timer.start();

        Image icon = new Image("file:src/main/resources/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Đếm thời gian
     */
    public void timeCount(Group root) {
        long now = System.currentTimeMillis();
        if (now - last_time > 1000) {
            last_time = System.currentTimeMillis();
            time.setText("Time: " + time_number);
            time_number--;
            if (time_number < 0) {
                gameOver(root);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

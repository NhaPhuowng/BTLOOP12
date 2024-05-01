package bomberman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class Bomberman extends Application {
    public void start(Stage stage) throws IOException {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/game-view.fxml")));
        Group root = new Group();
        Button button = new Button("nope");
        button.isCancelButton();
        root.getChildren().add(button);
        Scene scene = new Scene(root, Color.DARKVIOLET);
        stage.setScene(scene);
        stage.setWidth(720);
        stage.setHeight(480);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

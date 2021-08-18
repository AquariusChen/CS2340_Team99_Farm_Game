package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene scene;
    private static Media music = new Media(
            Main.class.getResource("4399_Farm_bgm.mp3").toExternalForm());
    private static MediaPlayer player = new MediaPlayer(music);

    //Start of Everything
    //Launch Welcome screen, set title, etc.
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Play Music
        player.setAutoPlay(true);
        player.play();

        Parent root = FXMLLoader.load(getClass().getResource("welcome/welcome.fxml"));
        scene = new Scene(root, 922, 700);
        primaryStage.setTitle("4399 Farm");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

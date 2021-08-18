package src.inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryDisplay extends Application {
    private Scene scene;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("inventoryFX.fxml"));
        scene = new Scene(root, 922, 700);
        primaryStage.setTitle("4399 Farm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

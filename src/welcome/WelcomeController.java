package src.welcome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class WelcomeController {
    @FXML
    private Button enter;

    //When Button is clicked, go to Configuration page
    @FXML
    public void goToConfiguration(ActionEvent event) throws Exception {
        Stage stage = (Stage) enter.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../configuration/Configuration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


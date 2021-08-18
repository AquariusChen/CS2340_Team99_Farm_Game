package src.end;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EndController {
    @FXML
    private Button restart;

    //When Button is clicked, go to Welcome page
    @FXML
    public void goToWelcome(ActionEvent event) throws Exception {
        Stage stage = (Stage) restart.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../welcome/Welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


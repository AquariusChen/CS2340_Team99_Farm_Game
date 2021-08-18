package src.win;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.stats.Stats;

public class WinningController {

    @FXML
    private Text totalMoney;

    @FXML
    private Text totalWater;

    @FXML
    private Text totalPlant;

    @FXML
    private Text totalPesticide;

    @FXML
    private Text totalFertilizer;

    @FXML
    private Button restartW;

    private int money;
    private int water;
    private int plant;
    private int pesticide;
    private int fertilizer;



    @FXML
    public void initialize() {
        money = Stats.getMoneyCount();
        water = Stats.getWaterCount();
        plant = Stats.getPlantCount();
        pesticide = Stats.getPesticideCount();
        fertilizer = Stats.getFertilizerCount();

        totalMoney.setText("" + money);
        totalWater.setText("" + water);
        totalPlant.setText("" + plant);
        totalPesticide.setText("" + pesticide);
        totalFertilizer.setText("" + fertilizer);
    }

    //When Button is clicked, go to Welcome page
    @FXML
    public void goToWelcome(ActionEvent event) throws Exception {
        Stage stage = (Stage) restartW.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../welcome/Welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

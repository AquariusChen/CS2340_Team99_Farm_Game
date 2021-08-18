package src.configuration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.inventory.InventoryInfo;
import src.market.Money;

public class ConfigurationController {

    private static String userName;
    private static int difficultLevel = 0;
    private static int startSeed = 0;
    private static int startSeason = 0;

    @FXML
    private TextField nametf;
    @FXML
    private RadioButton r1Diff;
    @FXML
    private RadioButton r2Diff;
    @FXML
    private RadioButton r3Diff;
    @FXML
    private RadioButton r1Seed;
    @FXML
    private RadioButton r2Seed;
    @FXML
    private RadioButton r3Seed;
    @FXML
    private RadioButton r1Season;
    @FXML
    private RadioButton r2Season;
    @FXML
    private RadioButton r3Season;
    @FXML
    private RadioButton r4Season;
    @FXML
    private ToggleGroup tgDiff;
    @FXML
    private ToggleGroup tgSeed;
    @FXML
    private ToggleGroup tgSeason;
    @FXML
    private Button back;
    @FXML
    private Button next;
    @FXML
    private Label message;

    @FXML
    public void initialize() {
        tgDiff.selectToggle(r1Diff);
        tgSeason.selectToggle(r1Season);
        tgSeed.selectToggle(r1Seed);
    }

    @FXML
    //If back arrow is clicked, go to Welcome Page
    public void goToStart(ActionEvent event) throws Exception {
        //Clear all the data selected
        nametf.clear();
        if (tgDiff.getSelectedToggle() != null) {
            tgDiff.getSelectedToggle().setSelected(false);
        }
        if (tgSeason.getSelectedToggle() != null) {
            tgSeed.getSelectedToggle().setSelected(false);
        }
        if (tgSeason.getSelectedToggle() != null) {
            tgSeason.getSelectedToggle().setSelected(false);
        }

        //Redirect to Welcome page
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../welcome/welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //If next arrow is clicked, go to Farm Page
    public void goToFarm(ActionEvent event) throws Exception {
        //Set userName
        userName = nametf.getText();
        if (nametf.getText() == null) {
            message.setText("Null name");
        } else if (nametf.getText().isEmpty() || nametf.getText().equals("")) {
            message.setText("Empty name");
        } else if (nametf.getText().trim().length() == 0) {
            message.setText("Blank name");
        } else {
            userName = nametf.getText().trim();
        }

        //Set Difficult Level
        if (r1Diff.isSelected()) {
            difficultLevel = 1;
        } else if (r2Diff.isSelected()) {
            difficultLevel = 2;
        } else if (r3Diff.isSelected()) {
            difficultLevel = 3;
        } else {
            message.setText("Missing Difficulty");
        }

        //Set Starting Seed
        if (r1Seed.isSelected()) {
            startSeed = 1; //radish
        } else if (r2Seed.isSelected()) {
            startSeed = 2; //watermelon
        } else if (r3Seed.isSelected()) {
            startSeed = 3; //eggplant
        } else {
            message.setText("Missing Seed Type");
        }

        //Set Starting Season
        if (r1Season.isSelected()) {
            startSeason = 1;
        } else if (r2Season.isSelected()) {
            startSeason = 2;
        } else if (r3Season.isSelected()) {
            startSeason = 3;
        } else if (r4Season.isSelected()) {
            startSeason = 4;
        } else {
            message.setText("Missing Season");
        }

        //Redirect to Farm
        if (!nametf.getText().isEmpty() && !(nametf.getText() == null)
                && nametf.getText().trim().length() != 0
                && tgDiff.getSelectedToggle().isSelected()
                && tgSeason.getSelectedToggle().isSelected()
                && tgSeed.getSelectedToggle().isSelected()) {

            //this will set the initial money in Money class
            Money.initialPlayerMoney();
            //this sets the initial seed in inventory
            InventoryInfo.setPlant(startSeed + 3, 1);

            Stage stage = (Stage) next.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../farm/Farm.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            nametf.clear();
        }
    }

    //Getters and Setters
    public static int getDifficultLevel() {
        return difficultLevel;
    }
    public static int getStartSeed() {
        return startSeed;
    }
    public static String getUsername() {
        return userName;
    }
    public static int getStartSeason() {
        return startSeason;
    }
}



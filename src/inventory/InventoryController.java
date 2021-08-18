package src.inventory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import src.farm.FarmController;
import java.io.IOException;

public class InventoryController {

    @FXML
    private Button farm;
    @FXML
    private Label type;
    @FXML
    private Label stage;
    @FXML
    private Label space;
    @FXML
    private Button sell;
    @FXML
    private Button plant;
    @FXML
    private Label item11;
    @FXML
    private ImageView itemB11;
    @FXML
    private Label item12;
    @FXML
    private ImageView itemB12;
    @FXML
    private Label item13;
    @FXML
    private ImageView itemB13;
    @FXML
    private Label item21;
    @FXML
    private ImageView itemB21;
    @FXML
    private Label item22;
    @FXML
    private ImageView itemB22;
    @FXML
    private Label item23;
    @FXML
    private ImageView itemB23;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button b5;
    @FXML
    private Button b6;

    private final String[] informationT = {"Radish", "Watermelon", "Eggplant"};
    private final String[] informationS = {"Crop", "Seed"};
    private static int initialSeed = FarmController.getCurSeed();
    private static int selectItem = 0;

    @FXML
    public void initialize() {
        //int initialSeed = FarmController.getCurSeed();
        //InventoryInfo.setPlant(initialSeed + 3,1);
        space.setText(InventoryInfo.getTotal() + " / " + InventoryInfo.getMAXSpace());

        item11.setText(String.valueOf(InventoryInfo.getPlant(1)));
        item12.setText(String.valueOf(InventoryInfo.getPlant(2)));
        item13.setText(String.valueOf(InventoryInfo.getPlant(3)));
        item21.setText(String.valueOf(InventoryInfo.getPlant(4)));
        item22.setText(String.valueOf(InventoryInfo.getPlant(5)));
        item23.setText(String.valueOf(InventoryInfo.getPlant(6)));

    }

    @FXML
    public void goToFarm(ActionEvent event) throws IOException {
        if (getSelectItem() != 0) {
            FarmController.setSeedToPlant(getSelectItem());
        }
        Stage pStage = (Stage) farm.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../farm/Farm.fxml"));
        Scene scene = new Scene(root);
        pStage.setScene(scene);
        pStage.show();
    }

    @FXML
    public void goToMarket(ActionEvent event) throws IOException {
        Stage pStage = (Stage) farm.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../market/Market.fxml"));
        Scene scene = new Scene(root);
        pStage.setScene(scene);
        pStage.show();
    }

    @FXML
    private void selectBox(ActionEvent event) {
        clearBoxOutline();
        Button button = (Button) event.getSource();
        if (button.equals(b1)) {
            type.setText(informationT[0]);
            stage.setText(informationS[0]);
            itemB11.setOpacity(1.0);
            selectItem = 1;
        } else if (button.equals(b2)) {
            type.setText(informationT[1]);
            stage.setText(informationS[0]);
            itemB12.setOpacity(1.0);
            selectItem = 2;
        } else if (button.equals(b3)) {
            type.setText(informationT[2]);
            stage.setText(informationS[0]);
            itemB13.setOpacity(1.0);
            selectItem = 3;
        } else if (button.equals(b4)) {
            type.setText(informationT[0]);
            stage.setText(informationS[1]);
            itemB21.setOpacity(1.0);
            selectItem = 4;
        } else if (button.equals(b5)) {
            type.setText(informationT[1]);
            stage.setText(informationS[1]);
            itemB22.setOpacity(1.0);
            selectItem = 5;
        } else if (button.equals(b6)) {
            type.setText(informationT[2]);
            stage.setText(informationS[1]);
            itemB23.setOpacity(1.0);
            selectItem = 6;
        }
    }


    private void clearBoxOutline() {
        itemB11.setOpacity(0.01);
        itemB12.setOpacity(0.01);
        itemB13.setOpacity(0.01);
        itemB21.setOpacity(0.01);
        itemB22.setOpacity(0.01);
        itemB23.setOpacity(0.01);
    }

    public static int getSelectItem() {
        return selectItem;
    }

    //    @FXML
    //    public void changeImage(ActionEvent event) {
    //        clearBoxOutline();
    //        Button button = (Button) event.getSource();
    //        for (int i = 0; i < 15; i++) {
    //            if (button.getId().equals(buttons[i])) {
    //                String t = informationT[itemList[i]%3];
    //                String s = informationS[(int)(itemList[i]/4)];
    //                if (itemList[i] == 0) {
    //                    t = "Empty";
    //                    s = "Empty";
    //                }
    //                type.setText(t);
    //                stage.setText(s);
    //                break;
    //            }
    //        }
    //        getButtonOutline(button).setOpacity(1);
    //    }

    //    private File rFile = new File("../images/plants/radish_mature.png");
    //    private String rPath = rFile.toURI().toString();
    //    private Image radish = new Image(rPath, 146, 117, false, true);
    //
    //    private File wFile = new File("../images/plants/watermelon_mature.png");
    //    private String wPath = wFile.toURI().toString();
    //    private Image water = new Image(wPath, 146, 117, false, true);
    //
    //    private File eFile = new File("../images/plants/eggplant_mature.png");
    //    private String ePath = eFile.toURI().toString();
    //    private Image eggplant = new Image(ePath, 146, 117, false, true);
    //
    //    private File rsFile = new File("../images/plants/radish_seed.png");
    //    private String rsPath = rsFile.toURI().toString();
    //    private Image radishs = new Image(rsPath, 123, 100, false, true);
    //
    //    private File wsFile = new File("../images/plants/watermelon_seed.png");
    //    private String wsPath = wsFile.toURI().toString();
    //    private Image watermetonSeed = new Image(wsPath, 123, 100, false, true);
    //
    //    private File esFile = new File("../images/plants/eggplant_seed.png");
    //    String esPath = esFile.toURI().toString();
    //    private Image eggplantSeed = new Image(esPath, 123, 100, false, true);
    //
    //    private final File emptyFile = new File("../images/emptyBlue.png");
    //    private final String emptyPath = emptyFile.toURI().toString();
    //    private Image empty = new Image(emptyPath);
}

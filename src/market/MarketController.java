package src.market;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import src.farm.FarmController;
import src.farm.FarmPlots;
import src.farm.Tips;
import src.inventory.InventoryInfo;

public class MarketController {

    //Current money.
    @FXML
    private Text marketMoney;
    @FXML
    private Text moneywarning;

    //Message (buy/sell)
    @FXML
    private Text warning;

    //Buttons that performs certain actions.
    @FXML
    private Button backFarm;
    @FXML
    private Button buy;
    @FXML
    private Button sell;

    //Checkboxes of buying seeds.
    @FXML
    private CheckBox buyR;
    @FXML
    private CheckBox buyE;
    @FXML
    private CheckBox buyW;
    @FXML
    private CheckBox fertilizer;
    @FXML
    private CheckBox pesticideBox;
    @FXML
    private CheckBox tractorBox;

    //Checkboxes of selling mature plants.
    @FXML
    private CheckBox sellR;
    @FXML
    private CheckBox sellE;
    @FXML
    private CheckBox sellW;

    //Checkboxes of selling seeds.
    @FXML
    private CheckBox sellRS;
    @FXML
    private CheckBox sellES;
    @FXML
    private CheckBox sellWS;

    //Prices of buying seeds.
    @FXML
    private Text rBuyPrice;
    @FXML
    private Text wBuyPrice;
    @FXML
    private Text eBuyPrice;

    //Prices of selling mature plants.
    @FXML
    private Text rSellPrice;
    @FXML
    private Text wSellPrice;
    @FXML
    private Text eSellPrice;

    //Prices of selling seeds.
    @FXML
    private Text rsSellPrice;
    @FXML
    private Text wsSellPrice;
    @FXML
    private Text esSellPrice;
    @FXML
    private Text fertilizerPrice;
    @FXML
    private Text pesticidePrice;

    //Plants/seeds quantity;
    @FXML
    private Text radishQuantity;
    @FXML
    private Text watermelonQuantity;
    @FXML
    private Text eggplantQuantity;
    @FXML
    private Text radishSeedQuantity;
    @FXML
    private Text watermelonSeedQuantity;
    @FXML
    private Text eggplantSeedQuantity;

    //Worker
    @FXML
    private RadioButton lowSkill;
    @FXML
    private RadioButton highSkill;
    @FXML
    private Text lsWage;
    @FXML
    private Text hsWage;

    //Machinery
    @FXML
    private CheckBox irrigation;
    @FXML
    private Button buyMachine;
    @FXML
    private Text irrigationSell;
    @FXML
    private Text tractorSell;

    //Current money.
    private static int m = 0;

    //Prices generated.
    private static int radishsell = Money.getPrice("seed_radish");
    private static int watermelonsell = Money.getPrice("seed_watermelon");
    private static int eggplantsell = Money.getPrice("seed_eggplant");
    private static int radishbuy = Money.getPrice("mature_radish");
    private static int watermelonbuy = Money.getPrice("mature_watermelon");
    private static int eggplantbuy = Money.getPrice("mature_eggplant");
    private static int radishseedbuy = Money.getPrice("radish");
    private static int watermelonseedbuy = Money.getPrice("watermelon");
    private static int eggplantseedbuy = Money.getPrice("eggplant");
    private static int lowSkillWage = Money.getPrice("lowSkillWorkerWage");
    private static int highSkillWage = Money.getPrice("highSkillWorkerWage");
    private static int fertilizersell = Money.getPrice("fertilizer");
    private static int pesticidesell = Money.getPrice("pesticide");
    private static int irrigationsell = Money.getPrice("irrigation");
    private static int tractorsell = Money.getPrice("tractor");

    //Quantity.
    private static int radishquantity = InventoryInfo.getQuantityOf("radish");
    private static int watermelonquantity = InventoryInfo.getQuantityOf("watermelon");
    private static int eggplantquantity = InventoryInfo.getQuantityOf("eggplant");
    private static int radishseedquantity = InventoryInfo.getQuantityOf("radish_seed");
    private static int watermelonseedquantity = InventoryInfo.getQuantityOf("watermelon_seed");
    private static int eggplantseedquantity = InventoryInfo.getQuantityOf("eggplant_seed");

    //Worker hired
    private static int hireWorker = 0;
    private static int dayHired = 0;

    //Set current money and prices for each plant.
    @FXML
    public void initialize() {
        m = Money.getPlayerMoney();
        marketMoney.setText(Integer.toString(m));

        rBuyPrice.setText(Integer.toString(radishsell));
        wBuyPrice.setText(Integer.toString(watermelonsell));
        eBuyPrice.setText(Integer.toString(eggplantsell));
        rSellPrice.setText(Integer.toString(radishbuy));
        wSellPrice.setText(Integer.toString(watermelonbuy));
        eSellPrice.setText(Integer.toString(eggplantbuy));
        rsSellPrice.setText(Integer.toString(radishseedbuy));
        wsSellPrice.setText(Integer.toString(watermelonseedbuy));
        esSellPrice.setText(Integer.toString(eggplantseedbuy));
        lsWage.setText(Integer.toString(lowSkillWage));
        hsWage.setText(Integer.toString(highSkillWage));
        fertilizerPrice.setText(Integer.toString(fertilizersell));
        pesticidePrice.setText(Integer.toString(pesticidesell));
        irrigationSell.setText(Integer.toString(irrigationsell));
        tractorSell.setText(Integer.toString(tractorsell));


        radishquantity = InventoryInfo.getQuantityOf("radish");
        watermelonquantity = InventoryInfo.getQuantityOf("watermelon");
        eggplantquantity = InventoryInfo.getQuantityOf("eggplant");
        radishseedquantity = InventoryInfo.getQuantityOf("radish_seed");
        watermelonseedquantity = InventoryInfo.getQuantityOf("watermelon_seed");
        eggplantseedquantity = InventoryInfo.getQuantityOf("eggplant_seed");

        radishQuantity.setText(Integer.toString(radishquantity));
        watermelonQuantity.setText(Integer.toString(watermelonquantity));
        eggplantQuantity.setText(Integer.toString(eggplantquantity));
        radishSeedQuantity.setText(Integer.toString(radishseedquantity));
        watermelonSeedQuantity.setText(Integer.toString(watermelonseedquantity));
        eggplantSeedQuantity.setText(Integer.toString(eggplantseedquantity));
    }

    //Return to farm page.
    @FXML
    public void goToFarm(ActionEvent event) throws Exception {
        Stage stage = (Stage) backFarm.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../farm/Farm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Buy seed and update money and inventory.
    @FXML
    public void buySeed(ActionEvent event) throws Exception {
        CheckBox[] boxes = {buyR, buyW, buyE};
        int selects = 0;
        m = Money.getPlayerMoney();
        for (CheckBox box : boxes) {
            if (box.isSelected()) {
                selects++;
            }
        }
        if (selects > InventoryInfo.hasSpace()) {
            warning.setText("No Space in your Inventory!");
        } else {
            String message = "Buy: ";
            int count = 0;
            boolean hasChecked = false;
            for (CheckBox box : boxes) {
                if (box.isSelected()) {
                    hasChecked = true;
                    message += box.getText() + " Seed, ";
                    box.setSelected(false);
                    if (count == 0 && m >= radishsell) {
                        m -= radishsell;
                        marketMoney.setText(Integer.toString(m));
                        radishseedquantity += 1;
                        InventoryInfo.setQuantityOf("radish_seed", radishseedquantity);
                        radishSeedQuantity.setText(Integer.toString(radishseedquantity));
                    } else if (count == 1 && m >= watermelonsell) {
                        m -= watermelonsell;
                        marketMoney.setText(Integer.toString(m));
                        watermelonseedquantity += 1;
                        InventoryInfo.setQuantityOf("watermelon_seed", watermelonseedquantity);
                        watermelonSeedQuantity.setText(Integer.toString(watermelonseedquantity));
                    } else if (count == 2 && m >= eggplantsell) {
                        m -= eggplantsell;
                        marketMoney.setText(Integer.toString(m));
                        eggplantseedquantity += 1;
                        InventoryInfo.setQuantityOf("eggplant_seed", eggplantseedquantity);
                        eggplantSeedQuantity.setText(Integer.toString(eggplantseedquantity));
                    } else if (m < radishsell || m < watermelonsell || m < eggplantsell) {
                        moneywarning.setText("Money not enough!");
                        return;
                    }
                }
                count++;
            }
            if (hasChecked) {
                message = message.substring(0, message.length() - 2);
                warning.setText(message);
            } else {
                warning.setText("Please select seed(s)");
            }
        }
        Money.setPlayerMoney(m);
    }
    //Sell plants/seeds and update money and inventory.
    @FXML
    public void sell(ActionEvent event) throws Exception {
        CheckBox[] boxes = {sellR, sellW, sellE, sellRS, sellWS, sellES};
        String message = "";
        String warnMessage = "";
        int count = 0;
        m = Money.getPlayerMoney();
        boolean hasChecked = false;
        for (CheckBox box : boxes) {
            if (box.isSelected()) {
                hasChecked = true;
                box.setSelected(false);
                if (count == 0) { 
                    if (radishquantity > 0) {
                        message += box.getText() + ", ";
                        m += radishbuy;
                        radishquantity -= 1;
                        InventoryInfo.setQuantityOf("radish",
                                radishquantity);
                        radishQuantity.setText(Integer.toString(radishquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "radish, ";
                    }
                } else if (count == 1) {
                    if (watermelonquantity > 0) {
                        message += box.getText() + ", ";
                        m += watermelonbuy;
                        watermelonquantity -= 1;
                        InventoryInfo.setQuantityOf("watermelon",
                                watermelonquantity);
                        watermelonQuantity.setText(Integer.toString(watermelonquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "watermelon, ";
                    }
                    marketMoney.setText(Integer.toString(m));
                } else if (count == 2) {
                    if (eggplantquantity > 0) {
                        message += box.getText() + ", ";
                        m += eggplantbuy;
                        eggplantquantity -= 1;
                        InventoryInfo.setQuantityOf("eggplant",
                                eggplantquantity);
                        eggplantQuantity.setText(Integer.toString(eggplantquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "eggplant, ";
                    }
                    marketMoney.setText(Integer.toString(m));
                } else if (count == 3) {
                    if (radishseedquantity > 0) {
                        message += box.getText() + ", ";
                        m += radishseedbuy;
                        radishseedquantity -= 1;
                        InventoryInfo.setQuantityOf("radish_seed",
                                radishseedquantity);
                        radishSeedQuantity.setText(Integer.toString(radishseedquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "radish seed, ";
                    }
                    marketMoney.setText(Integer.toString(m));
                } else if (count == 4) {
                    if (watermelonseedquantity > 0) {
                        message += box.getText() + ", ";
                        m += watermelonseedbuy;
                        watermelonseedquantity -= 1;
                        InventoryInfo.setQuantityOf("watermelon_seed",
                                watermelonseedquantity);
                        watermelonSeedQuantity.setText(Integer.toString(watermelonseedquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "watermelon seed, ";
                    }
                    marketMoney.setText(Integer.toString(m));
                } else if (count == 5) {
                    if (eggplantseedquantity > 0) {
                        message += box.getText() + ", ";
                        m += eggplantseedbuy;
                        eggplantseedquantity -= 1;
                        InventoryInfo.setQuantityOf("eggplant_seed",
                                eggplantseedquantity);
                        eggplantSeedQuantity.setText(Integer.toString(eggplantseedquantity));
                        marketMoney.setText(Integer.toString(m));
                    } else {
                        warnMessage += "eggplant seed, ";
                    }
                    marketMoney.setText(Integer.toString(m));
                }
            }
            count++;
        }
        if (!hasChecked) {
            warning.setText("Please select item(s)");
        } else if (warnMessage.isEmpty()) {
            message = message.substring(0, message.length() - 2);
            warning.setText("Sell: " + message);
        } else {
            if (message.isEmpty()) {
                warning.setText("=Not enough " + warnMessage + "in Inventory=");
            } else {
                warning.setText("Sell: " + message + "  \n =Not enough "
                        + warnMessage + "in Inventory=");
            }
        }
        Money.setPlayerMoney(m);
    }

    @FXML
    public void toHireWorker(ActionEvent event) {
        m = Money.getPlayerMoney();
        if (hireWorker != 0) {
            moneywarning.setText("Already hire one worker");
            return;
        }

        if (lowSkill.isSelected() && m >= lowSkillWage) {
            hireWorker = 1;
            m -= lowSkillWage;
            lowSkill.setSelected(false);
        } else if (highSkill.isSelected() && m >= highSkillWage) {
            hireWorker = 2;
            m -= highSkillWage;
            highSkill.setSelected(false);
        } else if (m < highSkillWage
                || m < lowSkillWage) {
            moneywarning.setText("Not Enough Money");
        }

        dayHired = FarmPlots.getDayNum();
        marketMoney.setText("" + m);
        Money.setPlayerMoney(m);
    }

    @FXML
    public void buyTools(ActionEvent event) {
        CheckBox[] boxes = {fertilizer, pesticideBox, tractorBox, irrigation};
        boolean hasChecked = false;
        String message = "Buy: ";
        int count = 0;
        m = Money.getPlayerMoney();
        for (CheckBox box : boxes) {
            if (box.isSelected()) {
                message += box.getText() + ",";
                hasChecked = true;
                box.setSelected(false);
                if (count ==  0 && m > fertilizersell) {
                    m -= fertilizersell;
                    marketMoney.setText(Integer.toString(m));
                    Tips.setFertQuantity(Tips.getFertQuantity() + 1);
                } else if (count == 1 && m > pesticidesell) {
                    m -= pesticidesell;
                    marketMoney.setText(Integer.toString(m));
                    Tips.setPestQuantity(Tips.getPestQuantity() + 1);
                } else if (count == 2 && m > tractorsell) {
                    m -= tractorsell;
                    marketMoney.setText(Integer.toString(m));
                    Tips.setTractorQuantity(Tips.getTractorQuantity() + 1);
                    FarmPlots.setHarvestMax(FarmPlots.getHarvestMax() + 4);
                } else if (count == 3 && m > irrigationsell) {
                    m -= irrigationsell;
                    marketMoney.setText(Integer.toString(m));
                    FarmPlots.setWaterQuantity(FarmPlots.getWaterQuantity() + 1);
                } else {
                    moneywarning.setText("Money not enough!");
                }
            }
            count++;
        }
        if (hasChecked) {
            message = message.substring(0, message.length() - 1);
            warning.setText(message);
        } else {
            warning.setText("Please select tool(s)");
        }
        Money.setPlayerMoney(m);
    }

    public static int getHireWorker() {
        return hireWorker;
    }

    public static void setHireWorker(int i) {
        hireWorker = i;
    }

    public static int getDayHired() {
        return dayHired;
    }

    public static int getRadishSellPrice() {
        return radishsell;
    }

    public static int getWatermelonPrice() {
        return watermelonsell;
    }

    public static int getEggplantSellPrice() {
        return eggplantsell;
    }

    public static int getMinSeedPrice() {
        int minPrice = radishsell;
        if (minPrice < watermelonsell) {
            minPrice = watermelonsell;
        }
        if (minPrice < eggplantsell) {
            minPrice = eggplantsell;
        }
        return minPrice;
    }

}

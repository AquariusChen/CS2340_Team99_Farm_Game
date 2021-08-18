package src.farm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.configuration.ConfigurationController;
import src.entity.*;
import src.inventory.InventoryController;
import src.inventory.InventoryInfo;
import src.market.MarketController;
import src.market.Money;
import src.stats.Stats;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class FarmController {

    public enum Mode {
        WATER, FERT, PEST, HARVEST
    }
    private Mode mode = Mode.HARVEST;

    //If you need to use getter or setter on any variable,
    // make sure it's private static
    //0: no seed
    //1: Radish
    //2: Watermelon
    //3: Eggplant
    private static String[] seeds = {"Select Seed", "Radish", "Watermelon", "Eggplant"};
    private static int currentSeed = ConfigurationController.getStartSeed();

    private static String username = ConfigurationController.getUsername();
    private static int difficultLevel = ConfigurationController.getDifficultLevel();
    private static int startSeason = ConfigurationController.getStartSeason();
    private static int moneyAmount  = 1000;
    private static boolean firstEnter = true;
    private static int seedToPlant = currentSeed + 3;
    private static int targetPlot;
    private static final int FERT_MAX = 3;

    /* Chuyi Chen M6 - HarvestMax */
    @FXML
    private Text harvestQuantity;
    @FXML
    private ImageView tractor;

    /* Chuyi Chen M5 - Fertilizer */
    private static Tooltip[] tips;
    @FXML
    private Button fertilize;
    @FXML
    private Text fertilizerQuantity;

    /* Ke Gong M5 - Pesticide */
    @FXML
    private Button pesticideButton;
    @FXML
    private Text pesticideQuantity;

    @FXML
    private Text waterQuantity;

    /* Ruiyi Gao M5 - Random Event */
    private static String randomEventType = "";

    //A list of elements that you need to change from FXML
    //Declare more elements here with the @FXML tag if you need to modify any
    @FXML
    private Text message;
    @FXML
    private Text money;
    @FXML
    private Text seed;
    @FXML
    private Text day;
    @FXML
    private Button goToInv;
    @FXML
    private Button goToMarket;
    @FXML
    private ImageView plant;
    @FXML
    private Text plotPrice;
    @FXML
    private ImageView unlockPlot;

    @FXML
    private ImageView farmPlot1;
    @FXML
    private ImageView farmPlot2;
    @FXML
    private ImageView farmPlot3;
    @FXML
    private ImageView farmPlot4;
    @FXML
    private ImageView farmPlot5;
    @FXML
    private ImageView farmPlot6;
    @FXML
    private ImageView farmPlot7;
    @FXML
    private ImageView farmPlot8;
    @FXML
    private ImageView farmPlot9;
    @FXML
    private ImageView farmPlot10;
    @FXML
    private ImageView farmPlot11;
    @FXML
    private ImageView farmPlot12;

    @FXML
    private Button water;
    @FXML
    private Text reminder;
    @FXML
    private ImageView plot1WaterLevel;
    @FXML
    private ImageView plot2WaterLevel;
    @FXML
    private ImageView plot3WaterLevel;
    @FXML
    private ImageView plot4WaterLevel;
    @FXML
    private ImageView plot5WaterLevel;
    @FXML
    private ImageView plot6WaterLevel;
    @FXML
    private ImageView plot7WaterLevel;
    @FXML
    private ImageView plot8WaterLevel;
    @FXML
    private ImageView plot9WaterLevel;
    @FXML
    private ImageView plot10WaterLevel;
    @FXML
    private ImageView plot11WaterLevel;
    @FXML
    private ImageView plot12WaterLevel;

    @FXML
    private Button tip1;
    @FXML
    private Button tip2;
    @FXML
    private Button tip3;
    @FXML
    private Button tip4;
    @FXML
    private Button tip5;
    @FXML
    private Button tip6;
    @FXML
    private Button tip7;
    @FXML
    private Button tip8;
    @FXML
    private Button tip9;
    @FXML
    private Button tip10;
    @FXML
    private Button tip11;
    @FXML
    private Button tip12;

    @FXML
    private Text randomEventMessage;
    @FXML
    private ImageView lowSkillWorker;
    @FXML
    private ImageView highSkillWorker;
    @FXML
    private Text lsText;
    @FXML
    private Text hsText;

    //Quantity
    private static int radishquantity = InventoryInfo.getQuantityOf("radish");
    private static int watermelonquantity = InventoryInfo.getQuantityOf("watermelon");
    private static int eggplantquantity = InventoryInfo.getQuantityOf("eggplant");
    private static Button[] b;

    //A method that will run EVERY time when the screen is displayed
    //Good for setting up stuff on the screen
    @FXML
    public void initialize() {

        //set the initial harvest quantity
        if(FarmPlots.getPreviousMax() != FarmPlots.getHarvestMax()) {
            tractor.setVisible(true);
            FarmPlots.resetPreviousMax();
        }
        harvestQuantity.setText("" + FarmPlots.getCurrHarvest());

        //set the money
        moneyAmount = Money.getPlayerMoney();
        money.setText(Integer.toString(moneyAmount));
        //set the current seed
        if (seedToPlant > 3) {
            seed.setText(seeds[seedToPlant - 3]);
        } else {
            seed.setText(seeds[0]);
        }

        if (!firstEnter) {
            day.setText(FarmPlots.getDay());
            ImageView[] farmPlot = {
                farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
                farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
            };
            ImageView[] waterLevel = {
                plot1WaterLevel, plot2WaterLevel, plot3WaterLevel, plot4WaterLevel,
                plot5WaterLevel, plot6WaterLevel, plot7WaterLevel, plot8WaterLevel,
                plot9WaterLevel, plot10WaterLevel, plot11WaterLevel, plot12WaterLevel
            };
            for (int i = 0; i < FarmPlots.getCropsOnFarm().length; i++) {
                farmPlot[i].setImage(new Image(FarmPlots.getCropsOnFarm()[i].getUrl()));
                waterLevel[i].setImage(new Image("images/water/water_level_"
                        + FarmPlots.getCropsOnFarm()[i].getWaterLevel() + ".png"));
            }
            plotPrice.setText("" + FarmPlots.getPlotPrice());
            if (FarmPlots.getCurrWaterQuantity() < FarmPlots.getWaterQuantity()) {
                waterQuantity.setText("" + FarmPlots.getCurrWaterQuantity());
            } else {
                waterQuantity.setText("" + FarmPlots.getWaterQuantity());
            }
        }

        if (firstEnter) {
            firstEnter = false;

            //just for demo purpose, comment out for final deployment
            //InventoryInfo.setQuantityOf("radish", 5);
            //InventoryInfo.setQuantityOf("eggplant", 5);
            //InventoryInfo.setQuantityOf("watermelon", 5);

            //Initialize Farm Plot (Random Type, Random Stage)
            FarmPlots.setFarmPlot(new ImageView[] {
                farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
                farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
            });

            Crop[] cropsOnFarm = new Crop[12];

            for (int i = 0; i < 8; i++) {
                cropsOnFarm[i] = new Placeholder();
                FarmPlots.setCropsOnFarm(cropsOnFarm);
                cropsOnFarm[i].setWaterLevel(1);
            }
            for (int i = 8; i < 12; i++) {
                cropsOnFarm[i] = new Lock();
                FarmPlots.setCropsOnFarm(cropsOnFarm);
                cropsOnFarm[i].setWaterLevel(1);
            }
            // Outdated code for M3
            /*
            Random rand = new Random();
            for (int i = 0; i < cropsOnFarm.length; i++) {
                //set stage
                int stage = rand.nextInt(3) + 1;
                switch (rand.nextInt(3) + 1) {
                case 1:
                    cropsOnFarm[i] = new Eggplant(stage);
                    break;
                case 2:
                    cropsOnFarm[i] = new Radish(stage);
                    break;
                case 3:
                    cropsOnFarm[i] = new Watermelon(stage);
                    break;
                default:
                    break;
                }
                FarmPlots.setCropsOnFarm(cropsOnFarm);
                //System.out.println(cropsOnFarm[i].getUrl());

                //set waterlevel
                cropsOnFarm[i].setWaterLevel(1);
            }*/
            for (int i = 0; i < cropsOnFarm.length; i++) {
                FarmPlots.getFarmPlot()[i].setImage(new Image(cropsOnFarm[i].getUrl()));
            }
        }
        Tips.initial(new Button[] {
            tip1, tip2, tip3, tip4, tip5, tip6,
            tip7, tip8, tip9, tip10, tip11, tip12
        }); //hovering tips for fertilizer (don't move it to other place)
        fertilizerQuantity.setText(Integer.toString(Tips.getFertQuantity()));
        pesticideQuantity.setText(Integer.toString(Tips.getPestQuantity()));
        radishquantity = InventoryInfo.getQuantityOf("radish");
        watermelonquantity = InventoryInfo.getQuantityOf("watermelon");
        eggplantquantity = InventoryInfo.getQuantityOf("eggplant");
    }

    // Base on whether waterMode is on, either harvest or water target plant
    @FXML
    public void clickOnPlot(MouseEvent event) throws Exception {
        ImageView image = (ImageView) event.getTarget();
        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;
        if (FarmPlots.getCropsOnFarm()[targetPlot].getUrl() == "@../../images/Locked.png") {
            reminder.setText("You have to purchase this plot first!");
            return;
        }
        switch (mode) {
        case WATER:
            watering(event);
            break;
        case FERT:
            fertilizingAction(event);
            break;
        case HARVEST:
            if (FarmPlots.getCropsOnFarm()[targetPlot].getStage() == 4) {
                clear(event);
            } else {
                harvest(event);
            }
            break;
        case PEST:
            pestingAction(event);
            break;
        default:
            break;
        }
    }


    @FXML
    public void buyLockedPlot(MouseEvent event) throws Exception {
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };
        targetPlot = 8;
        Crop c = FarmPlots.getCropsOnFarm()[targetPlot];
        int m = Money.getPlayerMoney();
        while (targetPlot < 11 && !c.getUrl().equals("@../../images/Locked.png")) {
            targetPlot++;
            c = FarmPlots.getCropsOnFarm()[targetPlot];
        }
        if (!c.getUrl().equals("@../../images/Locked.png")) {
            reminder.setText("There are no more plots to purchase!");
        } else {
            if (FarmPlots.getPlotPrice() > m) {
                reminder.setText("You don't have enough money!");
            } else {
                m -= FarmPlots.getPlotPrice();
                money.setText("" + m);
                FarmPlots.incrementPlotPrice();
                plotPrice.setText("" + FarmPlots.getPlotPrice());
                reminder.setText("You bought a new plot and the price "
                        + "of the next plot has increased to $"
                        + FarmPlots.getPlotPrice());
                Money.setPlayerMoney(m);
                FarmPlots.setCropOnFarm(new Placeholder(), targetPlot);
                FarmPlots.getFarmPlot()[targetPlot].setImage(
                        new Image("@../../images/emptyBKG.png"));
                farmPlot[targetPlot].setImage(new Image("@../../images/emptyBKG.png"));
            }
        }
    }

    @FXML
    public void clear(MouseEvent event) throws Exception {
        ImageView image = (ImageView) event.getTarget();
        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;

        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };

        ImageView[] waterLevel = {
            plot1WaterLevel, plot2WaterLevel, plot3WaterLevel, plot4WaterLevel,
            plot5WaterLevel, plot6WaterLevel, plot7WaterLevel, plot8WaterLevel,
            plot9WaterLevel, plot10WaterLevel, plot11WaterLevel, plot12WaterLevel
        };

        farmPlot[targetPlot].setImage(new Image("@../../images/emptyBKG.png"));
        FarmPlots.getFarmPlot()[targetPlot].setImage(new Image("@../../images/emptyBKG.png"));
        FarmPlots.getCropsOnFarm()[targetPlot] = new Placeholder();
        FarmPlots.getCropsOnFarm()[targetPlot].setWaterLevel(1);
        waterLevel[targetPlot]
                .setImage(new Image("images/water/water_level_1.png"));
    }

    //When Crop is clicked, trigger Harvest Action
    @FXML
    public void harvest(MouseEvent event) throws Exception {
        if (FarmPlots.getCurrHarvest() <= 0) {
            message.setText("No more harvest time today!");
            return;
        }

        ImageView image = (ImageView) event.getTarget();

        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;

        harvestOneCrop(targetPlot);
    }

    private void harvestOneCrop(int targetPlot) throws Exception {
        Crop c = FarmPlots.getCropsOnFarm()[targetPlot];
        //50% chance of bonus yield
        int yield = 1;
        if (c.getFertLevel() > 0) {
            yield += (new Random()).nextInt(2);
            if (InventoryInfo.hasSpace() <= yield) {
                message.setText("barn full");
                reminder.setText("");
                return;
            }
            if (yield > 1) {
                reminder.setText("Bonus Yield!");
            }
        } else {
            reminder.setText("");
        }


        if (c.getStage() == 3 && InventoryInfo.hasSpace() > 0) {
            //update harvest times if harvest successfully
            FarmPlots.setCurrHarvest(FarmPlots.getCurrHarvest() - 1);
            harvestQuantity.setText("" + FarmPlots.getCurrHarvest());

            ImageView[] farmPlot = {
                farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
                farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
            };

            ImageView[] waterLevel = {
                plot1WaterLevel, plot2WaterLevel, plot3WaterLevel, plot4WaterLevel,
                plot5WaterLevel, plot6WaterLevel, plot7WaterLevel, plot8WaterLevel,
                plot9WaterLevel, plot10WaterLevel, plot11WaterLevel, plot12WaterLevel
            };

            int m = Money.getPlayerMoney();

            if (c instanceof Radish) {
                if (c.getPesticide()) {
                    m += Money.getPrice("mature_radish") * 0.7;
                    Money.setPlayerMoney(m);
                    money.setText("" + m);
                    reminder.setText("This plant has been sprayed "
                            +  "so it's directly sold to the market!");
                } else {
                    radishquantity += yield;
                    InventoryInfo.setQuantityOf("radish", radishquantity);
                }
            } else if (c instanceof Watermelon) {
                if (c.getPesticide()) {
                    m += Money.getPrice("mature_watermelon") * 0.7;
                    Money.setPlayerMoney(m);
                    money.setText("" + m);
                    reminder.setText("This plant has been sprayed "
                            +  "so it's directly sold to the market!");
                } else {
                    watermelonquantity += yield;
                    InventoryInfo.setQuantityOf("watermelon", watermelonquantity);
                }
            } else if (c instanceof Eggplant) {
                if (c.getPesticide()) {
                    m += Money.getPrice("mature_eggplant") * 0.7;
                    Money.setPlayerMoney(m);
                    money.setText("" + m);
                    reminder.setText(
                            "This plant has been sprayed so it's directly sold to the market!"
                    );
                } else {
                    eggplantquantity += yield;
                    InventoryInfo.setQuantityOf("eggplant", eggplantquantity);
                }
            }
            //clear fertilizer level when harvest
            c.setFertLevel(0);
            Tips.updateTip(targetPlot);
            message.setText("harvest: " + FarmPlots.getCropsOnFarm()[targetPlot].toString());

            farmPlot[targetPlot].setImage(new Image("@../../images/emptyBKG.png"));
            FarmPlots.getFarmPlot()[targetPlot].setImage(new Image("@../../images/emptyBKG.png"));
            FarmPlots.getCropsOnFarm()[targetPlot] = new Placeholder();
            FarmPlots.getCropsOnFarm()[targetPlot].setWaterLevel(1);
            waterLevel[targetPlot]
                    .setImage(new Image("images/water/water_level_1.png"));

        } else if (InventoryInfo.hasSpace() <= 0) {
            message.setText("barn full");
        } else {
            message.setText("");
        }
    }

    //When Button is clicked, go to Inventory page
    @FXML
    public void goToInventory(ActionEvent event) throws Exception {
        Stage stage = (Stage) goToInv.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../inventory/Inventory.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //When Button is clicked, go to Inventory page
    @FXML
    public void goToMarket(ActionEvent event) throws Exception {
        Stage stage = (Stage) goToMarket.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../market/Market.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void plantSeed(MouseEvent event) throws Exception {

        targetPlot = 0;
        while (targetPlot < 11 && FarmPlots.getCropsOnFarm()[targetPlot].getStage() != -1) {
            targetPlot++;
        }
        Crop c = FarmPlots.getCropsOnFarm()[targetPlot];
        if (c.getUrl() == "@../../images/Locked.png") {
            reminder.setText("You have to purchase this plot first!");
            return;
        }
        int prevFert = c.getFertLevel();

        if (InventoryController.getSelectItem() != 0) {
            seedToPlant = InventoryController.getSelectItem();
        }

        if (targetPlot < 12 && seedToPlant > 3
                && InventoryInfo.getPlant(seedToPlant) > 0) {
            InventoryInfo.setPlant(seedToPlant,
                    InventoryInfo.getPlant(seedToPlant) - 1);
            ImageView[] farmPlot = {
                farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
                farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
            };
            String imageUrl = "@../../images/emptyBKG.png";
            if (seedToPlant == 4) {
                imageUrl = new Radish(1).getUrl();
                FarmPlots.getCropsOnFarm()[targetPlot] = new Radish(1);
                seed.setText("Radish");
            } else if (seedToPlant == 5) {
                imageUrl = new Watermelon(1).getUrl();
                FarmPlots.getCropsOnFarm()[targetPlot] = new Watermelon(1);
                seed.setText("Watermelon");
            } else if (seedToPlant == 6) {
                imageUrl = new Eggplant(1).getUrl();
                FarmPlots.getCropsOnFarm()[targetPlot] = new Eggplant(1);
                seed.setText("Eggplant");
            }
            //having previous fertilized land effect
            FarmPlots.getCropsOnFarm()[targetPlot].setFertLevel(prevFert);
            message.setText("Plant: " + FarmPlots.getCropsOnFarm()[targetPlot].toString());

            farmPlot[targetPlot].setImage(new Image(imageUrl));
            FarmPlots.getFarmPlot()[targetPlot].setImage(new Image(imageUrl));
            FarmPlots.getCropsOnFarm()[targetPlot].setWaterLevel(1);

        } else if (targetPlot == 12) {
            message.setText("No enough plot");
        } else if (seedToPlant <= 3) {
            message.setText("Selection is not a seed");
            seed.setText("Not a Seed");
        } else if (InventoryInfo.getPlant(seedToPlant) <= 0) {
            if (seedToPlant == 4) {
                seed.setText("Radish");
            } else if (seedToPlant == 5) {
                seed.setText("Watermelon");
            } else if (seedToPlant == 6) {
                seed.setText("Eggplant");
            }
            message.setText("Not enough seed");
        } else {
            message.setText("");
        }

        int plantCount = Stats.getPlantCount() + 1;
        Stats.setPlantCount(plantCount);

    }

    //Toggles Water Mode and Update Reminder
    @FXML
    public void waterPlant() {
        if (mode != Mode.WATER) {
            mode = Mode.WATER;
        } else {
            mode = Mode.HARVEST;
        }
        if (mode == Mode.WATER) {
            reminder.setText("Select Plant To Water");
        } else {
            reminder.setText("");
        }
    }

    //Water target plant
    @FXML
    public void watering(MouseEvent event) throws Exception {
        //Check if have enough water
        if (FarmPlots.getCurrWaterQuantity() <= 0) {
            reminder.setText("Your watering quota has reached maximum today!");
            return;
        } else {
            FarmPlots.decrementWater();
            waterQuantity.setText("" + FarmPlots.getCurrWaterQuantity());
        }
        //Identify which plot user clicked on
        ImageView image = (ImageView) event.getTarget();
        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;
        ImageView[] waterLevel = {
            plot1WaterLevel, plot2WaterLevel, plot3WaterLevel, plot4WaterLevel,
            plot5WaterLevel, plot6WaterLevel, plot7WaterLevel, plot8WaterLevel,
            plot9WaterLevel, plot10WaterLevel, plot11WaterLevel, plot12WaterLevel
        };
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };

        //get current water level
        int currentWL = FarmPlots.getCropsOnFarm()[targetPlot].getWaterLevel();

        //check if plant has died due to over watering
        if (currentWL > 3) {
            FarmPlots.getCropsOnFarm()[targetPlot].setStage(4);  //set plant stage to dead
            //update dead plant image
            farmPlot[targetPlot].setImage(new Image(FarmPlots.getCropsOnFarm()[targetPlot]
                    .getUrl()));
            reminder.setText("Too much Water");
        } else {
            //increase Water level
            currentWL++;
            FarmPlots.getCropsOnFarm()[targetPlot].setWaterLevel(currentWL);
        }

        //get corresponding image
        waterLevel[targetPlot]
                .setImage(new Image("images/water/water_level_" + currentWL + ".png"));

        int waterCount = Stats.getWaterCount() + 1;
        Stats.setWaterCount(waterCount);
    }

    @FXML
    public void goToNextDay(MouseEvent event) throws Exception {
        // Checks whether player wins
        if (Money.getPlayerMoney() >= 2000) {
            goToWinningPage(event);
        }

        FarmPlots.incrementDay();
        day.setText(FarmPlots.getDay());

        ImageView[] waterLevel = {
            plot1WaterLevel, plot2WaterLevel, plot3WaterLevel, plot4WaterLevel,
            plot5WaterLevel, plot6WaterLevel, plot7WaterLevel, plot8WaterLevel,
            plot9WaterLevel, plot10WaterLevel, plot11WaterLevel, plot12WaterLevel
        };
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };

        randomEventCalculator();
        //locust();

        //Reset available water quota
        FarmPlots.resetWater();
        waterQuantity.setText("" + FarmPlots.getCurrWaterQuantity());

        //updates fertilizer and water level
        for (int i = 0; i < farmPlot.length; i++) {
            Crop c = FarmPlots.getCropsOnFarm()[i];

            int currentFL = c.getFertLevel();
            int currentWL = c.getWaterLevel();

            if (c.getWaterLevel() > 1) {
                //increase one stage in growth cycle if immature
                if (currentFL > 0 && c.getStage() < 3) {
                    c.setStage(c.getStage() + 1);
                    farmPlot[i].setImage(new Image(c.getUrl()));
                }
                //normal growth
                if (c.getStage() < 3 && c.getStage() > 0) {
                    c.setStage(c.getStage() + 1);
                    farmPlot[i].setImage(new Image(c.getUrl()));
                }
            } else {
                //plant died
                c.setStage(4);
                farmPlot[i].setImage(new Image(c.getUrl()));
            }

            //updates water level
            if (currentWL > 1) {
                currentWL--;
            }

            //updates fertilizer level
            if (currentFL > 0) {
                c.setFertLevel(currentFL - 1);
                Tips.updateTip(targetPlot);
            }

            // Check whether the plant died
            if (currentWL >= 4) {
                currentWL = 4;
                c.setStage(4);
                farmPlot[i].setImage(new Image(c.getUrl()));
            }

            if (currentWL < 1) { //revised <=
                currentWL = 1;
                c.setStage(4);
                farmPlot[i].setImage(new Image(c.getUrl()));
            }

            waterLevel[i]
                    .setImage(new Image("images/water/water_level_" + currentWL + ".png"));
            c.setWaterLevel(currentWL);

        }

        // Hire Worker
        int worker = MarketController.getHireWorker();
        if (worker != 0) {
            if (worker == 1) {
                lowSkillWorker.setVisible(true);
                lsText.setVisible(true);
            } else {
                highSkillWorker.setVisible(true);
                hsText.setVisible(true);
            }
            MarketController.setHireWorker(0);
        } else {
            if (lowSkillWorker != null) {
                lowSkillWorker.setVisible(false);
                lsText.setVisible(false);
            }
            if (highSkillWorker != null) {
                highSkillWorker.setVisible(false);
                hsText.setVisible(false);
            }
        }

        //updates harvesting maximum
        FarmPlots.setCurrHarvest(FarmPlots.getHarvestMax());
        harvestQuantity.setText("" + FarmPlots.getCurrHarvest());
        //clear message
        message.setText("");
        // Check End Game
        endThisGame();
    }

    private boolean endGame = false;
    private boolean endGameBuffer = false;
    private void endThisGame() throws IOException {
        int minSeedPrice = MarketController.getMinSeedPrice();
        int playerMoney;
        boolean noCrop;
        int inventTotal;
        Crop[] crops;

        playerMoney = Money.getPlayerMoney();
        inventTotal = InventoryInfo.getTotal();
        crops = FarmPlots.getCropsOnFarm();
        noCrop = true;
        for (int i = 0; i < 12; i++) {
            if (crops[i].getStage() > 0 && crops[i].getStage() < 4) {
                noCrop = false;
                break;
            }
        }
        if (playerMoney < minSeedPrice && noCrop && inventTotal <= 0) {
            endGame = endGameBuffer;
            endGameBuffer = true;
        }
        if (endGame) {
            Stage stage = (Stage) goToInv.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().
                    getResource("../end/end.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    // Worker working
    @FXML
    public void lowSkillWork(MouseEvent event) throws Exception {
        int i = 0;
        int plot = 0;
        int radishNum = InventoryInfo.getPlant(1);
        int watermelonNum = InventoryInfo.getPlant(2);
        int eggplantNum = InventoryInfo.getPlant(3);
        while (i < 2 && plot < 12) {
            if (FarmPlots.getCropsOnFarm()[plot].getStage() == 3) {
                harvestOneCrop(plot);
                i++;
            }
            plot++;
        }

        int earning = (InventoryInfo.getPlant(1) - radishNum)
                * MarketController.getRadishSellPrice()
                + (InventoryInfo.getPlant(2) - watermelonNum)
                * MarketController.getWatermelonPrice()
                + (InventoryInfo.getPlant(3) - eggplantNum)
                * MarketController.getEggplantSellPrice();

        InventoryInfo.setPlant(1, radishNum);
        InventoryInfo.setPlant(2, watermelonNum);
        InventoryInfo.setPlant(3, eggplantNum);

        Money.setPlayerMoney(Money.getPlayerMoney() + earning);
        money.setText(Integer.toString(Money.getPlayerMoney()));
    }

    @FXML
    public void highSkillWork(MouseEvent event) throws Exception {
        int plot = 0;
        int radishNum = InventoryInfo.getPlant(1);
        int watermelonNum = InventoryInfo.getPlant(2);
        int eggplantNum = InventoryInfo.getPlant(3);
        while (plot < 12) {
            if (FarmPlots.getCropsOnFarm()[plot].getStage() == 3) {
                harvestOneCrop(plot);
            }
            plot++;
        }

        int earning = (InventoryInfo.getPlant(1) - radishNum)
                * MarketController.getRadishSellPrice()
                + (InventoryInfo.getPlant(2) - watermelonNum)
                * MarketController.getWatermelonPrice()
                + (InventoryInfo.getPlant(3) - eggplantNum)
                * MarketController.getEggplantSellPrice();

        InventoryInfo.setPlant(1, radishNum);
        InventoryInfo.setPlant(2, watermelonNum);
        InventoryInfo.setPlant(3, eggplantNum);

        Money.setPlayerMoney(Money.getPlayerMoney() + earning);
        money.setText(Integer.toString(Money.getPlayerMoney()));
    }


    //Random Event Section
    @FXML
    public void randomEventCalculator() {
        // Initial chance of happening
        int difficulty = ConfigurationController.getDifficultLevel();
        int chanceOfRain = 10; //5 means 5%
        int chanceOfDrought = 5;
        int chanceOfLocust = 5;

        // Adjust base on difficulty
        if (difficulty == 2) {
            chanceOfRain += 5;
            chanceOfDrought += 5;
            chanceOfLocust += 5;
        } else if (difficulty == 3) {
            chanceOfRain += 7;
            chanceOfDrought += 7;
            chanceOfLocust += 7;
        }

        // Decide whether each event will happen
        // For easy, if event < 5, rain. if 5 <= event < 10, drought. if 10 <= event < 12, locust.
        Random random = new Random();
        int event = random.nextInt(100);

        if (event < chanceOfRain) {
            rain();
            randomEventType = "rain";
        } else if (event < chanceOfRain + chanceOfDrought) {
            drought();
            randomEventType = "drought";
        } else if (event < chanceOfRain + chanceOfDrought + chanceOfLocust) {
            locust();
            randomEventType = "locust";
        } else {
            randomEventType = "";
            randomEventMessage.setText("");
        }
    }


    @FXML
    public void rain() {
        Random random = new Random();
        int waterLevelIncreased = random.nextInt(2) + 1; //Increase water level by 1 to 3 randomly
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };

        // Increase water level of each plant by waterLevelIncreased
        for (int i = 0; i < farmPlot.length; i++) {
            if (FarmPlots.getCropsOnFarm()[i] != null) {
                int currentWL = FarmPlots.getCropsOnFarm()[i].getWaterLevel();
                currentWL += waterLevelIncreased;
                FarmPlots.getCropsOnFarm()[i].setWaterLevel(currentWL);
            }
        }

        randomEventMessage.setText("It rained last night! All plants' waterlevel increased by "
                + waterLevelIncreased);
    };

    @FXML
    public void drought() {
        Random random = new Random();
        int waterLevelDecreased = random.nextInt(1) + 1; //Decrease water level by 1 to 2 randomly
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };

        // Decrease water level of each plant by waterLevelDereased
        for (int i = 0; i < farmPlot.length; i++) {
            int currentWL = FarmPlots.getCropsOnFarm()[i].getWaterLevel();
            currentWL -= waterLevelDecreased;
            FarmPlots.getCropsOnFarm()[i].setWaterLevel(currentWL);
        }

        randomEventMessage
                .setText("You encountered a drought! All plants' waterlevel decreased by "
                        + waterLevelDecreased);
    };

    @FXML
    public void locust() {
        // Calculate the possible number of plots affected base on difficulty
        int difficulty = ConfigurationController.getDifficultLevel();
        int minNumOfPlotsAffected = 1;
        int maxNumOfPlotsAffected = 3;
        if (difficulty == 2) {
            minNumOfPlotsAffected = 3;
            maxNumOfPlotsAffected = 5;
        } else if (difficulty == 3) {
            minNumOfPlotsAffected = 4;
            maxNumOfPlotsAffected = 6;
        }

        // Count how many crops are in farm
        // Prevent edge case: when crop left is < target, it will just keep looking for victim
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (!(FarmPlots.getCropsOnFarm()[i] instanceof Placeholder)) {
                count++;
            }
        }

        // Randomly select a num of plots affected
        Random random = new Random();
        int targetPlotNum = random.nextInt(maxNumOfPlotsAffected - minNumOfPlotsAffected)
                + minNumOfPlotsAffected;

        ArrayList<Integer> targetPlot = new ArrayList<Integer>();
        while (targetPlot.size() < targetPlotNum && targetPlot.size() < count) {
            int plotNum = random.nextInt(11) + 1; //Select Random Plot number from 1 to 12
            if (!targetPlot.contains(plotNum)
                    && !(FarmPlots.getCropsOnFarm()[plotNum - 1] instanceof Placeholder)) {
                targetPlot.add(plotNum);
            }
        }

        // Kill those plants
        ImageView[] farmPlot = {
            farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5, farmPlot6,
            farmPlot7, farmPlot8, farmPlot9, farmPlot10, farmPlot11, farmPlot12
        };
        for (int i = 0; i < 12; i++) {
            if (targetPlot.contains(i + 1) && !FarmPlots.getCropsOnFarm()[i].getPesticide()) {
                FarmPlots.getCropsOnFarm()[i].setStage(4);
                farmPlot[i].setImage(new Image(FarmPlots.getCropsOnFarm()[i]
                        .getUrl()));
            }
        }

        int numOfCropsDead = Math.min(targetPlot.size(), count);

        randomEventMessage.setText("You encountered Locust! " + numOfCropsDead
                + " of your crops were attacked!");

        System.out.println(targetPlot);
    }

    //Getters and Setters
    //Again, make sure they are static!!
    public static int getDifficultLevel() {
        return difficultLevel;
    }

    public static int getCurSeed() {
        return currentSeed;
    }

    public static String getUsername() {
        return username;
    }

    public static int getStartSeason() {
        return startSeason;
    }

    public static int getMoneyAmount() {
        return moneyAmount;
    }

    public static void setMoneyAmount(int value) {
        moneyAmount = value;
    }

    public static int getTargetPlot() {
        return targetPlot;
    }

    public static int getSeedToPlant() {
        return seedToPlant;
    }

    public static String getRandomEventType() {
        return randomEventType;
    }

    //4: radish
    //5: eggplant
    //6: watermelon
    public static void setSeedToPlant(int s) {
        seedToPlant = s;
    }

    /* Chuyi Chen M5 */
    //entering fertilizer mode
    @FXML
    public void fertilizing() {
        if (mode != Mode.FERT) {
            mode = Mode.FERT;
        } else {
            mode = Mode.HARVEST;
        }
        if (mode == Mode.FERT) {
            reminder.setText("Select Plant To Fertilize");
        } else {
            reminder.setText("");
        }
    }

    public void fertilizingAction(MouseEvent event) throws Exception {
        ImageView image = (ImageView) event.getTarget();
        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;

        //get current fertilizer level
        int currentFL = FarmPlots.getCropsOnFarm()[targetPlot].getFertLevel();
        if (Tips.getFertQuantity() <= 0) {
            reminder.setText("Not enough fertilizer");
            return;
        } else if (currentFL == FERT_MAX)  {
            reminder.setText("Maximum Fertilized Level");
            return;
        }

        //increase fertilizer level
        currentFL++;
        FarmPlots.getCropsOnFarm()[targetPlot].setFertLevel(currentFL);
        Tips.updateTip(targetPlot);
        reminder.setText("Fertilizer Level: " + currentFL);

        //decrease fertilizer quantity
        Tips.setFertQuantity(Tips.getFertQuantity() - 1);
        fertilizerQuantity.setText("" + Tips.getFertQuantity());

        int fertilizerCount = Stats.getFertilizerCount() + 1;
        Stats.setFertilizerCount(fertilizerCount);
    }

    @FXML
    public void pesting(ActionEvent event) {
        if (mode != Mode.PEST) {
            mode = Mode.PEST;
        } else {
            mode = Mode.HARVEST;
        }
        if (mode == Mode.PEST) {
            reminder.setText("Select Plant To Spray");
        } else {
            reminder.setText("");
        }
    }

    public void pestingAction(MouseEvent event) {
        ImageView image = (ImageView) event.getTarget();
        int targetPlot = Integer.parseInt(image.getId().substring(8)) - 1;

        Crop c = FarmPlots.getCropsOnFarm()[targetPlot];

        if (Tips.getPestQuantity() > 0 && !c.getPesticide()) {
            c.setPesticide();
            Tips.setPestQuantity(Tips.getPestQuantity() - 1);
            reminder.setText("Pesticide Sprayed");
        } else if (c.getPesticide()) {
            reminder.setText("Already Sprayed");
            return;
        } else {
            reminder.setText("Not enough Pesticide");
            return;
        }
        pesticideQuantity.setText("" + Tips.getPestQuantity());

        int pesticideCount = Stats.getPesticideCount() + 1;
        Stats.setPesticideCount(pesticideCount);

    }

    @FXML
    public void goToWinningPage(MouseEvent event) throws Exception {
        Stage stage = (Stage) day.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().
                getResource("../win/win.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

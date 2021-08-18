package src.farm;

import javafx.scene.image.ImageView;
import src.entity.Crop;

public class FarmPlots {
    private static ImageView[] farmPlot;
    private static Crop[] cropsOnFarm;
    private static int day = 1;
    private static int extraPlotPrice = 20;

    private static int water = 5;
    private static int currWater = 5;

    private static int previousMax = 4;
    private static int harvestMax = 4;
    private static int currHarvest = harvestMax;

    public static ImageView[] getFarmPlot() {
        return farmPlot;
    }

    public static void setFarmPlot(ImageView[] arr) {
        farmPlot = arr;
    }

    public static Crop[] getCropsOnFarm() {
        return cropsOnFarm;
    }

    public static void setCropsOnFarm(Crop[] arr) {
        cropsOnFarm = arr;
    }

    public static void setCropOnFarm(Crop c, int index) {
        cropsOnFarm[index] = c;
    }

    public static String getDay() {
        return ("DAY " + day);
    }

    public static void incrementDay() {
        day++;
    }

    public static int getDayNum() {
        return day;
    }

    public static int getPlotPrice() {
        return extraPlotPrice;
    }

    public static void incrementPlotPrice() {
        extraPlotPrice *= 2;
    }

    public static int getCurrWaterQuantity() {
        return currWater;
    }

    public static int getWaterQuantity() {
        return water;
    }

    public static void setWaterQuantity(int w) {
        water = w;
    }

    public static void decrementWater() {
        currWater--;
    }

    public static void resetWater() {
        currWater = water;
    }

    public static void setHarvestMax(int max) { harvestMax = max; }

    public static int getHarvestMax() { return harvestMax; }

    public static int getPreviousMax() { return previousMax; }
    
    public static void resetPreviousMax() { previousMax = harvestMax; }

    public static int getCurrHarvest() { return currHarvest; }

    public static void setCurrHarvest(int x) { currHarvest = x; }
}

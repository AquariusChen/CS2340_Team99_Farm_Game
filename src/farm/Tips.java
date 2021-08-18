package src.farm;

import javafx.scene.control.Button;

public class Tips {

    private static int fertQuantity = 0;
    private static int pestQuantity = 0;
    private static int tractorQuantity = 0;
    private static String message = "Fertilized Level: ";
    //rivate static Tooltip[] tips = new Tooltip[12];
    private static Button[] tip = new Button[12];


    public static void initial(Button[] b) {
        for (int i = 0; i < tip.length; i++) {
            tip[i] = b[i];
            tip[i].getTooltip().setText(message + FarmPlots.getCropsOnFarm()[i].getFertLevel());
            //Tooltip.install(FarmPlots.getFarmPlot()[i], tip[i].getTooltip());
        }
        /*
        for (int i = 0; i < 12; i++) {
            tips[i] = new Tooltip(message + FarmPlots.getCropsOnFarm()[i].getFertLevel());
            Tooltip.install(FarmPlots.getFarmPlot()[i], tips[i]);
        }
        return tips;*/
    }

    public static void updateTip(int index) {
        tip[index].getTooltip().setText(message + FarmPlots.getCropsOnFarm()[index].getFertLevel());
        //Tooltip.install(FarmPlots.getFarmPlot()[index], tip[index].getTooltip());
    }

    public static void setFertQuantity(int q) {
        fertQuantity = q;
    }

    public static int getFertQuantity() {
        return fertQuantity;
    }

    public static void setPestQuantity(int q) {
        pestQuantity = q;
    }

    public static int getPestQuantity() {
        return pestQuantity;
    }

    public static void setTractorQuantity(int q) {
        tractorQuantity = q;
    }

    public static int getTractorQuantity() {
        return tractorQuantity;
    }
}

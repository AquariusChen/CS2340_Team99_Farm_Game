package src.stats;

public class Stats {
    private static int moneyCount = 0;
    private static int waterCount = 0;
    private static int plantCount = 0;
    private static int pesticideCount = 0;
    private static int fertilizerCount = 0;

    // Getters and Setters
    public static void setMoneyCount(int moneyCount) {
        Stats.moneyCount = moneyCount;
    }

    public static void setWaterCount(int waterCount) {
        Stats.waterCount = waterCount;
    }

    public static void setPlantCount(int plantCount) {
        Stats.plantCount = plantCount;
    }

    public static void setPesticideCount(int pesticideCount) {
        Stats.pesticideCount = pesticideCount;
    }

    public static void setFertilizerCount(int fertilizerCount) {
        Stats.fertilizerCount = fertilizerCount;
    }

    public static int getMoneyCount() {
        return moneyCount;
    }

    public static int getWaterCount() {
        return waterCount;
    }

    public static int getPlantCount() {
        return plantCount;
    }

    public static int getPesticideCount() {
        return pesticideCount;
    }

    public static int getFertilizerCount() {
        return fertilizerCount;
    }
}



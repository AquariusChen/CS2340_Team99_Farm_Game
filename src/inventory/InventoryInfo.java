package src.inventory;

import java.util.HashMap;

/*
 * invenInfo:
 * (1) Total/current item size(s) (int)
 * (2) Maximum storage
 * (3) Each items’ quantity (or total number of each type) (map)
 */
public class InventoryInfo {

    private static final int MAX = 15;

    /**
     * each plant's quantity in inventory (need to set initial_seed to 1)
     *
     * Integer 1: radish
     * Integer 2: watermelon
     * Integer 3: eggplant
     *
     * Integer 4: radish seed
     * Integer 5: watermelon seed
     * Integer 6: eggplant seed
     *
     * Example: (Key, Value) -> (1, 2) -> quantity of radish is 2
     *
     */
    private static HashMap<Integer, Integer> quantity = new HashMap<Integer, Integer>() {
        {
            put(1, 0);
            put(2, 0);
            put(3, 0);

            put(4, 0);
            put(5, 0);
            put(6, 0);
        }
    };

    public static void clearAll() {
        quantity = new HashMap<Integer, Integer>() {
            {
                put(1, 0);
                put(2, 0);
                put(3, 0);

                put(4, 0);
                put(5, 0);
                put(6, 0);
            }
        };
    }

    public static HashMap<Integer, Integer> getQuantities() {
        return quantity;
    }

    public static Integer getPlant(int plant) {
        return quantity.get(plant);
    }

    public static void setPlant(int plant, int value) {
        quantity.put(plant, value);
    }

    public static int getTotal() {
        int totalItems = 0;
        for (int q : quantity.values()) {
            totalItems += q;
        }
        return totalItems;
    }

    public static int getMAXSpace() {
        return MAX;
    }

    public static int hasSpace() {
        return MAX - getTotal();
    }

    /**
     * Wrong plant name if get -1
     * plant_name =>
     * 1) radish
     * 2) watermelon
     * 3) eggplant
     *
     * 4) radish_seed
     * 5) watermelon_seed
     * 6) eggplant_seed
     * @param plantName the kind of plant we want to get quantity of
     * @return the quantity of that plant
     */
    public static int getQuantityOf(String plantName) {
        int res = -1;
        switch (plantName) {
        case "radish":
            res = quantity.getOrDefault(1, -1);
            break;
        case "watermelon":
            res = quantity.getOrDefault(2, -1);
            break;
        case "eggplant":
            res = quantity.getOrDefault(3, -1);
            break;
        case "radish_seed":
            res = quantity.getOrDefault(4, -1);
            break;
        case "watermelon_seed":
            res = quantity.getOrDefault(5, -1);
            break;
        case "eggplant_seed":
            res = quantity.getOrDefault(6, -1);
            break;
        default:
            System.out.println("You may input wrong plant_name.");
            break;
        }
        return res;
    }

    /**
     * plant_name =>
     * 1) radish / radish_seed
     * 2) watermelon / watermelon_seed
     * 3) eggplant / eggplant_seed
     *
     * Example: we have 2 radish but we want to set radish quantity to 8
     *
     * @param plantName the name of the plant want to change
     * @param number the quantity of the plant want to change
     */
    public static void setQuantityOf(String plantName, int number) {
        if (number >= 0) {
            switch (plantName.toLowerCase()) {
            case "radish":
                quantity.put(1, number);
                break;
            case "watermelon":
                quantity.put(2, number);
                break;
            case "eggplant":
                quantity.put(3, number);
                break;
            case "radish_seed":
                quantity.put(4, number);
                break;
            case "watermelon_seed":
                quantity.put(5, number);
                break;
            case "eggplant_seed":
                quantity.put(6, number);
                break;
            default:
                System.out.println("You may input wrong plant_name in setQuantityOf().");
                break;
            }
        } else {
            System.out.println("Input negative number in setQuantityOf(), so fails.");
        }
    }
}

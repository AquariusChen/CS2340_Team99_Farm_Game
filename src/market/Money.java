package src.market;

import src.configuration.ConfigurationController;
import src.stats.Stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//this class deals with player money and market price
public class Money {

    //player money
    private static int playerMoney = 0;

    //this will also set the initial money
    public static int initialPlayerMoney() {
        int initialM = 0;
        if (difficulty == 3) {
            initialM = 200;
        } else if (difficulty == 2) {
            initialM = 500;
        } else if (difficulty == 1) {
            initialM = 1000;
        }
        playerMoney = initialM;
        Stats.setMoneyCount(initialM);
        return initialM;
    }

    //final price = base * (difficulty_multiplier) + random_var
    //random_var
    private static Random rand = new Random();

    //difficulty_multiplier
    private static int difficulty = ConfigurationController.getDifficultLevel();

    //base price for all the seeds and mature plants
    private static Map<String, Integer> basePrice = new HashMap<String, Integer>() {
        {
            //IMPORTANT: input these name as String into getPrice(String base_name) method
            // example: getPrice("seed_watermelon")
            // Buy price
            put("seed_watermelon", 17);
            put("seed_radish", 16);
            put("seed_eggplant", 15);
            put("lowSkillWorkerWage", 80);
            put("highSkillWorkerWage", 120);
            put("fertilizer", 10);
            put("pesticide", 10);
            put("irrigation", 30);
            put("tractor", 150);

            //Sell price
            put("mature_watermelon", 45);
            put("mature_radish", 35);
            put("mature_eggplant", 30);
            put("watermelon", 4);
            put("radish", 3);
            put("eggplant", 2);
        }
    };

    public static int getPrice(String baseName) {
        int randomVar = rand.nextInt(10);
        // for debug: if you enter the wrong name, you will get $(lower_than_base) for price
        return basePrice.getOrDefault(baseName, -randomVar) * Math.abs(difficulty - 4) + randomVar;
    }

    public static int getPlayerMoney() {
        return playerMoney;
    }

    public static void setPlayerMoney(int money) {
        playerMoney = money;
        Stats.setMoneyCount(money);
    }
}

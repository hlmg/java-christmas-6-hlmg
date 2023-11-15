package christmas;

import christmas.common.Config;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new Config().getChristmasController();
        christmasController.run();
    }
}

package christmas.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Optional<Menu> from(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findAny();
    }

    public int getPrice() {
        return price;
    }

    public boolean isDessert() {
        return this == CHOCOLATE_CAKE || this == ICE_CREAM;
    }

    public boolean isMain() {
        return this == T_BONE_STEAK || this == BARBECUE_RIBS || this == SEAFOOD_PASTA || this == CHRISTMAS_PASTA;
    }

    public boolean isNotBeverage() {
        return this != ZERO_COKE && this != RED_WINE && this != CHAMPAGNE;
    }

    public String getName() {
        return name;
    }
}

package christmas.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6_000, Type.APPETIZER),
    TAPAS("타파스", 5_500, Type.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8_000, Type.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_000, Type.MAIN),
    BARBECUE_RIBS("바비큐립", 54_000, Type.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_000, Type.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, Type.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_000, Type.DESSERT),
    ICE_CREAM("아이스크림", 5_000, Type.DESSERT),
    ZERO_COKE("제로콜라", 3_000, Type.BEVERAGE),
    RED_WINE("레드와인", 60_000, Type.BEVERAGE),
    CHAMPAGNE("샴페인", 25_000, Type.BEVERAGE);

    private static final Map<String, Menu> cache;
    private final String name;
    private final int price;
    private final Type type;

    Menu(String name, int price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    static {
        cache = Arrays.stream(Menu.values())
                .collect(Collectors.toMap(Menu::getName, menu -> menu));
    }

    public static Optional<Menu> from(String name) {
        return Optional.ofNullable(cache.get(name));
    }

    public boolean isDessert() {
        return this.type == Type.DESSERT;
    }

    public boolean isMain() {
        return this.type == Type.MAIN;
    }

    public boolean isNotBeverage() {
        return this.type != Type.BEVERAGE;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private enum Type {
        APPETIZER, MAIN, DESSERT, BEVERAGE
    }
}

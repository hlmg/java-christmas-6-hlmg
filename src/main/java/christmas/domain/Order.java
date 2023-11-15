package christmas.domain;

import static christmas.common.ExceptionMessages.INVALID_ORDER;
import static christmas.common.ExceptionMessages.ONLY_BEVERAGES_NOT_ALLOWED;
import static christmas.common.ExceptionMessages.INVALID_ORDER_MENU_QUANTITY;

import java.util.HashSet;
import java.util.List;

public final class Order {
    private static final int MAX_ORDER_MENU_QUANTITY = 20;

    private final List<OrderMenu> orderMenus;

    private Order(List<OrderMenu> orderMenus) {
        this.orderMenus = List.copyOf(orderMenus);
    }

    public static Order from(List<OrderMenu> orderMenus) {
        validateDuplicate(orderMenus);
        validateOnlyBeverages(orderMenus);
        validateOrderMenuQuantity(orderMenus);
        return new Order(orderMenus);
    }

    private static void validateDuplicate(List<OrderMenu> orderMenus) {
        HashSet<OrderMenu> distinctOrderMenu = new HashSet<>();
        for (OrderMenu menuName : orderMenus) {
            if (!distinctOrderMenu.add(menuName)) {
                throw new IllegalArgumentException(INVALID_ORDER.getMessage());
            }
        }
    }

    private static void validateOnlyBeverages(List<OrderMenu> orderMenus) {
        orderMenus.stream()
                .filter(OrderMenu::isNotBeverage)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ONLY_BEVERAGES_NOT_ALLOWED.getMessage()));
    }

    private static void validateOrderMenuQuantity(List<OrderMenu> orderMenus) {
        int orderMenuQuantity = orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
        if (orderMenuQuantity > MAX_ORDER_MENU_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER_MENU_QUANTITY.getMessage());
        }
    }

    public int getTotalOrderAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getPrice)
                .sum();
    }

    public int getDessertMenuQuantity() {
        return orderMenus.stream()
                .filter(OrderMenu::isDessert)
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }

    public int getMainMenuQuantity() {
        return orderMenus.stream()
                .filter(OrderMenu::isMain)
                .mapToInt(OrderMenu::getQuantity)
                .sum();
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }
}

package christmas.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Order {
    private final List<OrderMenu> orderMenus;

    private Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static Order from(List<OrderMenu> orderMenus) {
        validateDuplicate(orderMenus);
        validateOnlyBeverage(orderMenus);
        validateOrderQuantity(orderMenus);
        return new Order(orderMenus);
    }

    private static void validateDuplicate(List<OrderMenu> orderMenus) {
        HashSet<OrderMenu> distinctOrderMenu = new HashSet<>();
        for (OrderMenu menuName : orderMenus) {
            if (!distinctOrderMenu.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private static void validateOnlyBeverage(List<OrderMenu> orderMenus) {
        orderMenus.stream()
                .filter(OrderMenu::isNotBeverage)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 음료만 주문 시, 주문할 수 없습니다."));
    }

    private static void validateOrderQuantity(List<OrderMenu> orderMenus) {
        int orderQuantity = orderMenus.stream()
                .mapToInt(OrderMenu::getQuantity)
                .sum();
        if (orderQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");
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
        return Collections.unmodifiableList(orderMenus);
    }
}

package christmas.domain;

import java.util.HashSet;
import java.util.List;

public class Order {
    private final List<OrderMenu> orderMenus;

    private Order(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public static Order from(List<OrderMenu> orderMenus) {
        validateDuplicate(orderMenus);
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
}

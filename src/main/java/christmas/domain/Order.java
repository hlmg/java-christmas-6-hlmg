package christmas.domain;

import java.util.HashSet;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static Order from(List<OrderItem> orderItems) {
        validateDuplicate(orderItems);
        return new Order(orderItems);
    }

    private static void validateDuplicate(List<OrderItem> orderItems) {
        HashSet<OrderItem> distinctOrderItem = new HashSet<>();
        for (OrderItem menuName : orderItems) {
            if (!distinctOrderItem.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    public int getTotalOrderAmount() {
        return orderItems.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();
    }

    public int getDessertMenuQuantity() {
        return orderItems.stream()
                .filter(OrderItem::isDessert)
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public int getMainMenuQuantity() {
        return orderItems.stream()
                .filter(OrderItem::isMain)
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }
}

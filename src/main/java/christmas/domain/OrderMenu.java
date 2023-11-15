package christmas.domain;

import static christmas.common.ExceptionMessages.INVALID_ORDER;

import java.util.Objects;

public final class OrderMenu {
    private static final int MIN_ORDER_QUANTITY = 1;

    private final Menu menu;
    private final int quantity;

    public OrderMenu(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderMenu from(String menuName, int quantity) {
        Menu menu = getMenuFrom(menuName);
        validateQuantity(quantity);
        return new OrderMenu(menu, quantity);
    }

    private static Menu getMenuFrom(String menuName) {
        return Menu.from(menuName)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_ORDER.getMessage()));
    }

    private static void validateQuantity(int quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public int getPrice() {
        return menu.getPrice() * quantity;
    }

    public boolean isNotBeverage() {
        return menu.isNotBeverage();
    }

    public boolean isDessert() {
        return menu.isDessert();
    }

    public boolean isMain() {
        return menu.isMain();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMenuName() {
        return menu.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderMenu orderMenu)) {
            return false;
        }
        return menu == orderMenu.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}

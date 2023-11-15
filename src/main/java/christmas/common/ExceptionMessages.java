package christmas.common;

public enum ExceptionMessages {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER_MENU_QUANTITY("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다."),
    ONLY_BEVERAGES_NOT_ALLOWED("음료만 주문 시, 주문할 수 없습니다.");

    private static final String MESSAGE_FORMAT = "[ERROR] %s";
    private final String message;

    ExceptionMessages(String message) {
        this.message = String.format(MESSAGE_FORMAT, message);
    }

    public String getMessage() {
        return message;
    }
}

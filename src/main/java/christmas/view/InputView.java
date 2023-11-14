package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderMenuDto;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    public static final String INPUT_VISIT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_ORDER_MENU = "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String INVALID_VISIT_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final Pattern VISIT_DATE_PATTERN = Pattern.compile("^([1-9]|[12]\\d|3[01])$");
    public static final Pattern ORDER_MENU_PATTERN = Pattern.compile("^([가-힣a-zA-Z]+-[0-9]+,?)+$");
    public static final String ORDER_MENU_SEPARATOR = ",";
    public static final String MENU_QUANTITY_SEPARATOR = "-";

    public int getVisitDate() {
        System.out.println(INPUT_VISIT_DATE);
        String visitDateInput = Console.readLine();
        validateNumeric(visitDateInput);
        return Integer.parseInt(visitDateInput);
    }

    private void validateNumeric(String visitDateInput) {
        if (!VISIT_DATE_PATTERN.matcher(visitDateInput).find()) {
            throw new IllegalArgumentException(INVALID_VISIT_DATE);
        }
    }

    public List<OrderMenuDto> getOrderItems() {
        System.out.println(INPUT_ORDER_MENU);
        String orderMenuInput = Console.readLine();
        validateFormat(orderMenuInput);
        return orderMenuDtosFrom(orderMenuInput);
    }

    private void validateFormat(String orderMenuInput) {
        if (!ORDER_MENU_PATTERN.matcher(orderMenuInput).find()) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }

    private List<OrderMenuDto> orderMenuDtosFrom(String orderMenuInput) {
        return Arrays.stream(orderMenuInput.split(ORDER_MENU_SEPARATOR))
                .map(orderMenu -> orderMenu.split(MENU_QUANTITY_SEPARATOR))
                .map(menuAndQuantity -> new OrderMenuDto(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1])))
                .collect(Collectors.toList());
    }
}

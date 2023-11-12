package christmas.view;

import christmas.dto.OrderItemResponseDto;
import java.util.List;

public class OutputView {

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreview(int dayOfMonth) {
        String message = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", dayOfMonth);
        System.out.println(message);
    }

    /*

    <주문 메뉴>
    티본스테이크 1개
    바비큐립 1개
    초코케이크 2개
    제로콜라 1개
     */
    public void printOrderItems(List<OrderItemResponseDto> orderItemResponseDtos) {
        System.out.print("\n<주문 메뉴>\n");

        StringBuilder stringBuilder = new StringBuilder();
        for (OrderItemResponseDto orderItemResponseDto : orderItemResponseDtos) {
            stringBuilder.append(
                    String.format("%s %d개\n", orderItemResponseDto.menuName(), orderItemResponseDto.quantity()));
        }
        System.out.print(stringBuilder);
    }

    /*

    <할인 전 총주문 금액>
    142,000원
     */
    public void printTotalOrderAmount(int totalOrderAmount) {
        System.out.print("\n<할인 전 총주문 금액>\n");
        System.out.printf("%,d원\n", totalOrderAmount);
    }
}

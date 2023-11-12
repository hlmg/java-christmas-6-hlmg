package christmas.view;

import christmas.dto.BenefitDetailDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionMenuDto;
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
    public void printOrderItems(List<OrderMenuDto> orderMenuDtos) {
        System.out.print("\n<주문 메뉴>\n");

        StringBuilder stringBuilder = new StringBuilder();
        for (OrderMenuDto orderMenuDto : orderMenuDtos) {
            stringBuilder.append(
                    String.format("%s %d개\n", orderMenuDto.menuName(), orderMenuDto.quantity()));
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

    /*

    <증정 메뉴>
    샴페인 1개
     */
    public void printPromotionMenu(List<PromotionMenuDto> promotionMenuDtos) {
        System.out.print("\n<증정 메뉴>\n");

        StringBuilder stringBuilder = new StringBuilder();
        for (PromotionMenuDto promotionMenuDto : promotionMenuDtos) {
            stringBuilder.append(String.format("%s %d개\n", promotionMenuDto.menuName(), promotionMenuDto.quantity()));
        }
        System.out.println(stringBuilder);
    }

    /*

    <혜택 내역>
    크리스마스 디데이 할인: -1,200원
    평일 할인: -4,046원
    특별 할인: -1,000원
    증정 이벤트: -25,000원
    */
    public void printBenefitDetails(List<BenefitDetailDto> benefitDetailDtos) {
        System.out.print("\n<혜택 내역>\n");

        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitDetailDto benefitDetailDto : benefitDetailDtos) {
            stringBuilder.append(String.format("%s -%,d원\n", benefitDetailDto.eventName(), benefitDetailDto.price()));
        }
        System.out.println(stringBuilder);
    }

    /*

    <총혜택 금액>
    -31,246원
    */
    public void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.print("\n<총혜택 금액>\n");

        System.out.printf("-%,d원\n", totalBenefitAmount);
    }

    /*

    <할인 후 예상 결제 금액>
    135,754원
     */
    public void printPaymentAmount(int paymentAmount) {
        System.out.print("\n<할인 후 예상 결제 금액>\n");

        System.out.printf("%,d원\n", paymentAmount);
    }

    /*

    <12월 이벤트 배지>
    산타
     */
    public void printEventBadge(String eventBadge) {
        System.out.print("\n<할인 후 예상 결제 금액>\n");

        System.out.printf("%s\n", eventBadge);
    }
}

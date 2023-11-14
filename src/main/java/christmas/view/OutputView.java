package christmas.view;

import christmas.dto.BenefitDto;
import christmas.dto.BenefitPreview;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionMenuDto;
import java.util.List;

public class OutputView {
    public static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String INFORMATION_HEADER_FORMAT = "\n<%s>\n";
    private static final String ORDER_MENU = "주문 메뉴";
    private static final String TOTAL_ORDER_AMOUNT = "할인 전 총주문 금액";
    private static final String PROMOTION_MENU = "증정 메뉴";
    private static final String BENEFIT_DETAILS = "혜택 내역";
    private static final String TOTAL_BENEFIT_AMOUNT = "총혜택 금액";
    private static final String PAYMENT_AMOUNT = "할인 후 예상 결제 금액";
    private static final String EVENT_BADGE = "12월 이벤트 배지";
    public static final String NOTING = "없음";

    public void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreview(int dayOfMonth) {
        String message = String.format(EVENT_PREVIEW_MESSAGE, dayOfMonth);
        System.out.println(message);
    }

    public void printOrderItems(List<OrderMenuDto> orderMenuDtos) {
        System.out.printf(INFORMATION_HEADER_FORMAT, ORDER_MENU);

        StringBuilder stringBuilder = new StringBuilder();
        for (OrderMenuDto orderMenuDto : orderMenuDtos) {
            stringBuilder.append(
                    String.format("%s %d개\n", orderMenuDto.menuName(), orderMenuDto.quantity()));
        }
        System.out.print(stringBuilder);
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        System.out.printf(INFORMATION_HEADER_FORMAT, TOTAL_ORDER_AMOUNT);
        System.out.printf("%,d원\n", totalOrderAmount);
    }

    public void printBenefitPreview(BenefitPreview benefitPreview) {
        printPromotionMenu(benefitPreview.promotionMenuDtos());
        printBenefits(benefitPreview.benefitDtos());
        printTotalBenefitAmount(benefitPreview.totalBenefitAmount());
        printPaymentAmount(benefitPreview.paymentAmount());
        printEventBadge(benefitPreview.eventBadge());
    }

    private void printPromotionMenu(List<PromotionMenuDto> promotionMenuDtos) {
        System.out.printf(INFORMATION_HEADER_FORMAT, PROMOTION_MENU);

        if (promotionMenuDtos.isEmpty()) {
            System.out.println(NOTING);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (PromotionMenuDto promotionMenuDto : promotionMenuDtos) {
            stringBuilder.append(String.format("%s %d개\n", promotionMenuDto.menuName(), promotionMenuDto.quantity()));
        }
        System.out.print(stringBuilder);
    }

    private void printBenefits(List<BenefitDto> benefitDtos) {
        System.out.printf(INFORMATION_HEADER_FORMAT, BENEFIT_DETAILS);

        if (benefitDtos.isEmpty()) {
            System.out.println(NOTING);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitDto benefitDto : benefitDtos) {
            stringBuilder.append(String.format("%s -%,d원\n", benefitDto.eventName(), benefitDto.price()));
        }
        System.out.print(stringBuilder);
    }

    private void printTotalBenefitAmount(int totalBenefitAmount) {
        System.out.printf(INFORMATION_HEADER_FORMAT, TOTAL_BENEFIT_AMOUNT);

        if (totalBenefitAmount == 0) {
            System.out.println(NOTING);
            return;
        }

        System.out.printf("-%,d원\n", totalBenefitAmount);
    }

    private void printPaymentAmount(int paymentAmount) {
        System.out.printf(INFORMATION_HEADER_FORMAT, PAYMENT_AMOUNT);

        if (paymentAmount == 0) {
            System.out.println(NOTING);
            return;
        }

        System.out.printf("%,d원\n", paymentAmount);
    }

    private void printEventBadge(String eventBadge) {
        System.out.printf(INFORMATION_HEADER_FORMAT, EVENT_BADGE);

        System.out.printf("%s\n", eventBadge);
    }
}

package christmas.view;

import christmas.dto.BenefitDto;
import christmas.dto.BenefitPreviewDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.PromotionMenuDto;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public final class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String INFORMATION_HEADER_FORMAT = "%n<%s>%n";
    private static final String ORDER_MENU = "주문 메뉴";
    private static final String TOTAL_ORDER_AMOUNT = "할인 전 총주문 금액";
    private static final String PROMOTION_MENU = "증정 메뉴";
    private static final String BENEFIT_DETAILS = "혜택 내역";
    private static final String TOTAL_BENEFIT_AMOUNT = "총혜택 금액";
    private static final String PAYMENT_AMOUNT = "할인 후 예상 결제 금액";
    private static final String EVENT_BADGE = "12월 이벤트 배지";
    private static final String NOTING = "없음";

    public void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreview(LocalDate date) {
        String message = String.format(EVENT_PREVIEW_MESSAGE, date.getDayOfMonth());
        System.out.println(message);
    }

    public void printOrderItems(List<OrderMenuDto> orderMenuDtos) {
        printHeader(ORDER_MENU);
        printPairContents(orderMenuDtos, "%s %d개%n", OrderMenuDto::menuName, OrderMenuDto::quantity);
    }

    public void printTotalOrderAmount(int totalOrderAmount) {
        printHeader(TOTAL_ORDER_AMOUNT);
        printAmount(totalOrderAmount);
    }

    public void printBenefitPreview(BenefitPreviewDto benefitPreviewDto) {
        printPromotionMenu(benefitPreviewDto.promotionMenuDtos());
        printBenefits(benefitPreviewDto.benefitDtos());
        printTotalBenefitAmount(benefitPreviewDto.totalBenefitAmount());
        printPaymentAmount(benefitPreviewDto.paymentAmount());
        printEventBadge(benefitPreviewDto.eventBadge());
    }

    private void printPromotionMenu(List<PromotionMenuDto> promotionMenuDtos) {
        printHeader(PROMOTION_MENU);
        printPairContents(promotionMenuDtos, "%s %d개%n", PromotionMenuDto::menuName, PromotionMenuDto::quantity);
    }

    private void printBenefits(List<BenefitDto> benefitDtos) {
        printHeader(BENEFIT_DETAILS);
        printPairContents(benefitDtos, "%s -%,d원%n", BenefitDto::eventName, BenefitDto::price);
    }

    private void printTotalBenefitAmount(int totalBenefitAmount) {
        printHeader(TOTAL_BENEFIT_AMOUNT);
        printAmount(-totalBenefitAmount);
    }

    private void printPaymentAmount(int paymentAmount) {
        printHeader(PAYMENT_AMOUNT);
        printAmount(paymentAmount);
    }

    private void printEventBadge(String eventBadge) {
        printHeader(EVENT_BADGE);
        System.out.printf("%s%n", eventBadge);
    }

    private <T> void printPairContents(List<T> contents, String format, Function<T, String> key,
                                       ToIntFunction<T> value) {
        if (contents.isEmpty()) {
            System.out.println(NOTING);
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (T content : contents) {
            stringBuilder.append(String.format(format, key.apply(content), value.applyAsInt(content)));
        }
        System.out.print(stringBuilder);
    }

    private void printAmount(int amount) {
        if (amount == 0) {
            System.out.println(NOTING);
            return;
        }
        System.out.printf("%,d원%n", amount);
    }

    private void printHeader(String header) {
        System.out.printf(INFORMATION_HEADER_FORMAT, header);
    }
}

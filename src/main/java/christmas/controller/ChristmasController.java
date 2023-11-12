package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.Plan;
import christmas.domain.VisitDate;
import christmas.domain.event.BenefitDetail;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.EventManager;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import christmas.dto.BenefitDetailDto;
import christmas.dto.OrderItemRequestDto;
import christmas.dto.OrderItemResponseDto;
import christmas.dto.PromotionMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(),
                    new WeekdayEvent(),
                    new WeekendEvent(),
                    new SpecialEvent(),
                    new PromotionEvent()));

    public void run() {
        outputView.printWelcome();

        int dayOfMonth = inputView.getVisitDate();
        VisitDate visitDate = VisitDate.from(dayOfMonth);

        List<OrderItemRequestDto> OrderItemRequestDtos = inputView.getOrderItems();
        List<OrderItem> orderItems = OrderItemRequestDtos.stream()
                .map(orderItem -> OrderItem.from(orderItem.menuName(), orderItem.quantity()))
                .toList();
        Order order = Order.from(orderItems);

        outputView.printEventPreview(dayOfMonth);

        // TODO: requestDto 그대로 반환해도 될듯, request response 하나로 합치기 or request dto에서 뭔가 하기
        List<OrderItemResponseDto> orderItemResponseDtos = orderItems.stream().map(orderItem -> {
            String menuName = orderItem.getMenuName();
            int quantity = orderItem.getQuantity();
            return new OrderItemResponseDto(menuName, quantity);
        }).toList();
        outputView.printOrderItems(orderItemResponseDtos);

        Plan plan = Plan.from(visitDate, order);

        // plan이 service 역할을 하는 것 같아서, 없애는 것 고려
        int totalOrderAmount = plan.getTotalOrderAmount();
        outputView.printTotalOrderAmount(totalOrderAmount);

        List<BenefitDetail> benefitDetails = eventManager.getBenefitDetails(plan);

        // 증정된 메뉴 가져오기
        Map<String, Integer> promotionMenus = benefitDetails.stream()
                .map(BenefitDetail::getPromotionMenus)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Menu::getName, Collectors.summingInt(unused -> 1)));

        List<PromotionMenuDto> promotionMenuDtos = promotionMenus.entrySet().stream()
                .map(entry -> new PromotionMenuDto(entry.getKey(), entry.getValue()))
                .toList();

        outputView.printPromotionMenu(promotionMenuDtos);

        List<BenefitDetailDto> benefitDetailDtos = new ArrayList<>();
        for (BenefitDetail benefitDetail : benefitDetails) {
            String eventName = benefitDetail.getEvent().getName();
            int discountAmount = benefitDetail.getBenefitAmount();
            benefitDetailDtos.add(new BenefitDetailDto(eventName, discountAmount));
        }

        outputView.printBenefitDetails(benefitDetailDtos);

        int totalBenefitAmount = 0;
        for (BenefitDetail benefitDetail : benefitDetails) {
            totalBenefitAmount += benefitDetail.getBenefitAmount();
        }

        outputView.printTotalBenefitAmount(totalBenefitAmount);

//        outputView.printPaymentAmount();

//        outputView.printEventBadge();

    }
}

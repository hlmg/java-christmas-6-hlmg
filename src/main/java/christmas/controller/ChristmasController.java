package christmas.controller;

import christmas.domain.ChristmasService;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.dto.BenefitPreview;
import christmas.dto.PlanRequest;
import christmas.dto.OrderMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ChristmasService christmasService = new ChristmasService();

    public void run() {
        outputView.printWelcome();

        PlanRequest planRequest = createPlanRequest();

        BenefitPreview benefitPreview = christmasService.plan(planRequest);

        outputView.printBenefitPreview(benefitPreview);
    }

    private PlanRequest createPlanRequest() {
        VisitDate visitDate = exceptionHandleAndRetry(this::getVisitDate);
        Order order = exceptionHandleAndRetry(this::getOrder);

        outputView.printEventPreview(visitDate.getDate());
        outputView.printOrderItems(orderMenuDtoFrom(order));
        outputView.printTotalOrderAmount(order.getTotalOrderAmount());

        return new PlanRequest(visitDate, order);
    }

    private VisitDate getVisitDate() {
        int dayOfMonth = inputView.getVisitDate();
        return VisitDate.from(dayOfMonth);
    }

    private Order getOrder() {
        List<OrderMenuDto> orderMenuDtos = inputView.getOrderItems();
        return orderFrom(orderMenuDtos);
    }

    private Order orderFrom(List<OrderMenuDto> orderMenuDtos) {
        List<OrderMenu> orderMenus = orderMenuDtos.stream()
                .map(orderItem -> OrderMenu.from(orderItem.menuName(), orderItem.quantity()))
                .toList();
        return Order.from(orderMenus);
    }

    private List<OrderMenuDto> orderMenuDtoFrom(Order order) {
        return order.getOrderMenus().stream()
                .map(orderMenu -> new OrderMenuDto(orderMenu.getMenuName(), orderMenu.getQuantity()))
                .toList();
    }

    private <T> T exceptionHandleAndRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

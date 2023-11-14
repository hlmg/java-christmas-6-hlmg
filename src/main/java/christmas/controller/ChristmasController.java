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
        int dayOfMonth = inputView.getVisitDate();
        VisitDate visitDate = VisitDate.from(dayOfMonth);

        List<OrderMenuDto> orderMenuDtos = inputView.getOrderItems();
        Order order = orderFrom(orderMenuDtos);

        outputView.printEventPreview(dayOfMonth);
        outputView.printOrderItems(orderMenuDtos);
        outputView.printTotalOrderAmount(order.getTotalOrderAmount());
        return new PlanRequest(visitDate, order);
    }

    private Order orderFrom(List<OrderMenuDto> orderMenuDtos) {
        List<OrderMenu> orderMenus = orderMenuDtos.stream()
                .map(orderItem -> OrderMenu.from(orderItem.menuName(), orderItem.quantity()))
                .toList();
        return Order.from(orderMenus);
    }
}

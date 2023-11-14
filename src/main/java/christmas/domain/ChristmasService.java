package christmas.domain;

import christmas.domain.benefit.Benefit;
import christmas.domain.event.ChristmasDDayEvent;
import christmas.domain.event.PromotionEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import christmas.dto.BenefitDto;
import christmas.dto.BenefitPreview;
import christmas.dto.PlanRequest;
import christmas.dto.PromotionMenuDto;
import java.util.List;
import java.util.Map;

public final class ChristmasService {
    private final EventManager eventManager = EventManager.from(
            List.of(new ChristmasDDayEvent(),
                    new WeekdayEvent(),
                    new WeekendEvent(),
                    new SpecialEvent(),
                    new PromotionEvent()));

    public BenefitPreview plan(PlanRequest planRequest) {
        VisitDate visitDate = planRequest.visitDate();
        Order order = planRequest.order();

        AppliedBenefits appliedBenefits = eventManager.apply(visitDate, order);

        List<PromotionMenuDto> promotionMenuDtos = promotionMenuDtosFrom(appliedBenefits);
        List<BenefitDto> benefitDtos = benefitDtosFrom(appliedBenefits);
        int totalBenefitAmount = appliedBenefits.getTotalBenefitAmount();
        int paymentAmount = order.getTotalOrderAmount() - appliedBenefits.getTotalDiscountAmount();
        String eventBadge = EventBadge.from(totalBenefitAmount).getName();

        return new BenefitPreview(promotionMenuDtos, benefitDtos, totalBenefitAmount, paymentAmount, eventBadge);
    }

    private List<PromotionMenuDto> promotionMenuDtosFrom(AppliedBenefits appliedBenefits) {
        Map<String, Integer> promotionMenus = appliedBenefits.getPromotionMenus();
        return promotionMenus.entrySet().stream()
                .map(entry -> new PromotionMenuDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    private List<BenefitDto> benefitDtosFrom(AppliedBenefits appliedBenefits) {
        List<Benefit> discountBenefits = appliedBenefits.getBenefits();
        return discountBenefits.stream()
                .map(benefit -> new BenefitDto(benefit.getEventName(), benefit.getBenefitAmount()))
                .toList();
    }
}

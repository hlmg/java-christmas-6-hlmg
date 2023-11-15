package christmas.domain;

import christmas.domain.benefit.Benefit;
import christmas.dto.BenefitDto;
import christmas.dto.BenefitPreviewDto;
import christmas.dto.PlanRequest;
import christmas.dto.PromotionMenuDto;
import java.util.List;
import java.util.Map;

public final class ChristmasService {
    private final EventManager eventManager;

    public ChristmasService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public BenefitPreviewDto plan(PlanRequest planRequest) {
        VisitDate visitDate = planRequest.visitDate();
        Order order = planRequest.order();

        AppliedBenefits appliedBenefits = eventManager.apply(visitDate, order);

        return benefitPreviewFrom(appliedBenefits, order);
    }

    private BenefitPreviewDto benefitPreviewFrom(AppliedBenefits appliedBenefits, Order order) {
        List<PromotionMenuDto> promotionMenuDtos = promotionMenuDtosFrom(appliedBenefits);
        List<BenefitDto> benefitDtos = benefitDtosFrom(appliedBenefits);
        int totalBenefitAmount = appliedBenefits.getTotalBenefitAmount();
        int paymentAmount = appliedBenefits.getPaymentAmount(order.getTotalOrderAmount());
        String eventBadge = EventBadge.from(totalBenefitAmount).getName();

        return new BenefitPreviewDto(promotionMenuDtos, benefitDtos, totalBenefitAmount, paymentAmount, eventBadge);
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

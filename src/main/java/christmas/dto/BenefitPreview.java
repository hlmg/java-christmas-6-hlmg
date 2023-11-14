package christmas.dto;


import java.util.List;

public record BenefitPreview(
        List<PromotionMenuDto> promotionMenuDtos,
        List<BenefitDto> benefitDtos,
        int totalBenefitAmount,
        int paymentAmount,
        String eventBadge
) {
}

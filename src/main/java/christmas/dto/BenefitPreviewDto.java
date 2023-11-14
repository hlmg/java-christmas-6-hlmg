package christmas.dto;


import java.util.List;

public record BenefitPreviewDto(
        List<PromotionMenuDto> promotionMenuDtos,
        List<BenefitDto> benefitDtos,
        int totalBenefitAmount,
        int paymentAmount,
        String eventBadge
) {
}

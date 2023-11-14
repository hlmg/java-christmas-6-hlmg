package christmas.domain;

import christmas.domain.benefit.Benefit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AppliedBenefits {
    private final List<Benefit> benefits;

    private AppliedBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static AppliedBenefits from(List<Benefit> benefits) {
        return new AppliedBenefits(benefits);
    }

    public Map<String, Integer> getPromotionMenus() {
        return benefits.stream()
                .map(Benefit::getPromotionMenu)
                .flatMap(Optional::stream)
                .collect(Collectors.groupingBy(Menu::getName, Collectors.summingInt(unused -> 1)));
    }

    public int getTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        for (Benefit benefit : benefits) {
            totalBenefitAmount += benefit.getBenefitAmount();
        }
        return totalBenefitAmount;
    }

    public int getTotalDiscountAmount() {
        int totalDiscountAmount = 0;
        for (Benefit benefit : benefits) {
            totalDiscountAmount += benefit.getDiscountAmount();
        }
        return totalDiscountAmount;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }
}

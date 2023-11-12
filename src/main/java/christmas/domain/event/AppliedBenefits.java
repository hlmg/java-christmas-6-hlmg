package christmas.domain.event;

import christmas.domain.Menu;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppliedBenefits {
    private final List<Benefit> benefits;

    private AppliedBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static AppliedBenefits from(List<Benefit> benefits) {
        return new AppliedBenefits(benefits);
    }

    public Map<String, Integer> getPromotionMenus() {
        return benefits.stream()
                .map(Benefit::getPromotionMenus)
                .flatMap(Collection::stream)
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

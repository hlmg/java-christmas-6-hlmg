package christmas.domain;

import christmas.domain.benefit.Benefit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class AppliedBenefits {
    private final List<Benefit> benefits;

    private AppliedBenefits(List<Benefit> benefits) {
        this.benefits = List.copyOf(benefits);
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
        return benefits.stream()
                .mapToInt(Benefit::getBenefitAmount)
                .sum();
    }

    private int getTotalDiscountAmount() {
        return benefits.stream()
                .mapToInt(Benefit::getDiscountAmount)
                .sum();
    }

    public int getPaymentAmount(int totalOrderAmount) {
        return totalOrderAmount - getTotalDiscountAmount();
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }
}

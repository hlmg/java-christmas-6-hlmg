package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountBenefit;
import christmas.domain.benefit.PromotionBenefit;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class AppliedBenefitsTest {

    // 총혜택 금액: 할인 금액 + 증정 메뉴 가격
    @Test
    void 총혜택_금액을_계산할_수_있다() {
        List<Benefit> benefits = List.of(
                DiscountBenefit.from("event1", 1000),
                DiscountBenefit.from("event2", 2000),
                PromotionBenefit.from("event3", Menu.BUTTON_MUSHROOM_SOUP),
                PromotionBenefit.from("event4", Menu.RED_WINE)
        );
        AppliedBenefits appliedBenefits = AppliedBenefits.from(benefits);

        int actual = appliedBenefits.getTotalBenefitAmount();

        assertThat(actual).isEqualTo(1000 + 2000 + 6000 + 60000);
    }

    // 결제 금액: 총주문 금액 - 할인 금액
    @Test
    void 결제_금액을_계산할_수_있다() {
        List<Benefit> benefits = List.of(
                DiscountBenefit.from("event1", 1000),
                DiscountBenefit.from("event2", 2000),
                PromotionBenefit.from("event3", Menu.BUTTON_MUSHROOM_SOUP),
                PromotionBenefit.from("event4", Menu.RED_WINE)
        );
        AppliedBenefits appliedBenefits = AppliedBenefits.from(benefits);

        int actual = appliedBenefits.getPaymentAmount(100_000);

        assertThat(actual).isEqualTo(100_000 - (1000 + 2000));
    }

    @Test
    void 증정된_메뉴_목록을_가져올_수_있다() {
        List<Benefit> benefits = List.of(
                PromotionBenefit.from("event1", Menu.BUTTON_MUSHROOM_SOUP),
                PromotionBenefit.from("event2", Menu.RED_WINE),
                PromotionBenefit.from("event3", Menu.RED_WINE),
                PromotionBenefit.from("event4", Menu.RED_WINE)
        );
        AppliedBenefits appliedBenefits = AppliedBenefits.from(benefits);

        Map<String, Integer> promotionMenus = appliedBenefits.getPromotionMenus();

        assertThat(promotionMenus.get("양송이수프")).isEqualTo(1);
        assertThat(promotionMenus.get("레드와인")).isEqualTo(3);
    }
}

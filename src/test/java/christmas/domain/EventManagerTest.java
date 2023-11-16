package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.DiscountBenefit;
import christmas.domain.benefit.PromotionBenefit;
import christmas.domain.event.DecemberEvent;
import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class EventManagerTest {
    private static final EventManager eventManager = EventManager.from(
            List.of(new AlwaysDiscountEvent(), new AlwaysPromotionEvent(), new ClosedEvent()));

    @Test
    void 총주문_금액이_10000원_미만이면_이벤트가_적용되지_않는다() {
        AppliedBenefits appliedBenefits = eventManager.apply(VisitDate.from(1),
                Order.from(List.of(OrderMenu.from("타파스", 1))));

        assertThat(appliedBenefits.getBenefits()).isEmpty();
    }

    @Test
    void 적용된_혜택_목록을_생성할_수_있다() {

        AppliedBenefits appliedBenefits = eventManager.apply(VisitDate.from(1),
                Order.from(List.of(OrderMenu.from("티본스테이크", 1))));

        assertThat(appliedBenefits.getBenefits()).isEqualTo(
                List.of(DiscountBenefit.from("discount", 1000), PromotionBenefit.from("promotion", Menu.CHAMPAGNE)));
    }

    private static class AlwaysDiscountEvent extends DecemberEvent {
        @Override
        protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
            return true;
        }

        @Override
        public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
            return DiscountBenefit.from("discount", 1000);
        }
    }

    private static class AlwaysPromotionEvent extends DecemberEvent {
        @Override
        protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
            return true;
        }

        @Override
        public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
            return PromotionBenefit.from("promotion", Menu.CHAMPAGNE);
        }
    }

    private static class ClosedEvent extends DecemberEvent {
        @Override
        protected boolean isSatisfiedCondition(VisitDate visitDate, Order order) {
            return false;
        }

        @Override
        public Benefit getBenefitFrom(VisitDate visitDate, Order order) {
            return PromotionBenefit.from("close", Menu.CHRISTMAS_PASTA);
        }
    }
}

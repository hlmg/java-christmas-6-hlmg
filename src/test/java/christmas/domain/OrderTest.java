package christmas.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class OrderTest {

    @Test
    void 중복_메뉴가_있으면_예외가_발생한다() {
        List<OrderItem> orderItems = List.of(
                OrderItem.from("타파스", 1),
                OrderItem.from("타파스", 2)
        );

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> Order.from(orderItems));
    }
}

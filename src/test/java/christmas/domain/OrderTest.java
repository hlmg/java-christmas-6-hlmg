package christmas.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Order.from(orderItems));
    }

    @Test
    void 총주문_금액을_계산할_수_있다() {
        List<OrderItem> orderItems = List.of(
                OrderItem.from("타파스", 1),
                OrderItem.from("아이스크림", 2)
        );
        Order order = Order.from(orderItems);

        int totalOrderAmount = order.getTotalOrderAmount();

        assertThat(totalOrderAmount).isEqualTo(15500);
    }

    @Test
    void 디저트_메뉴_수량을_계산할_수_있다() {
        Order order = Order.from(List.of(
                OrderItem.from("양송이수프", 1),
                OrderItem.from("초코케이크", 2),
                OrderItem.from("아이스크림", 5)
        ));

        int dessertMenuQuantity = order.getDessertMenuQuantity();

        assertThat(dessertMenuQuantity).isEqualTo(7);
    }

    @Test
    void 메인_메뉴_수량을_계산할_수_있다() {
        Order order = Order.from(List.of(
                OrderItem.from("티본스테이크", 1),
                OrderItem.from("바비큐립", 2),
                OrderItem.from("제로콜라", 5)
        ));

        int dessertMenuQuantity = order.getMainMenuQuantity();

        assertThat(dessertMenuQuantity).isEqualTo(3);
    }
}

package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class OrderTest {

    @Test
    void 중복_메뉴가_있으면_예외가_발생한다() {
        List<OrderMenu> orderMenus = List.of(
                OrderMenu.from("타파스", 1),
                OrderMenu.from("타파스", 2)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Order.from(orderMenus));
    }

    @Test
    void 총주문_금액을_계산할_수_있다() {
        List<OrderMenu> orderMenus = List.of(
                OrderMenu.from("타파스", 1),
                OrderMenu.from("아이스크림", 2)
        );
        Order order = Order.from(orderMenus);

        int totalOrderAmount = order.getTotalOrderAmount();

        assertThat(totalOrderAmount).isEqualTo(15500);
    }

    @Test
    void 디저트_메뉴_수량을_계산할_수_있다() {
        Order order = Order.from(List.of(
                OrderMenu.from("양송이수프", 1),
                OrderMenu.from("초코케이크", 2),
                OrderMenu.from("아이스크림", 5)
        ));

        int dessertMenuQuantity = order.getDessertMenuQuantity();

        assertThat(dessertMenuQuantity).isEqualTo(7);
    }

    @Test
    void 메인_메뉴_수량을_계산할_수_있다() {
        Order order = Order.from(List.of(
                OrderMenu.from("티본스테이크", 1),
                OrderMenu.from("바비큐립", 2),
                OrderMenu.from("제로콜라", 5)
        ));

        int dessertMenuQuantity = order.getMainMenuQuantity();

        assertThat(dessertMenuQuantity).isEqualTo(3);
    }

    @Test
    void 주문_수량이_20개가_넘으면_예외가_발생한다() {
        List<OrderMenu> orderMenus = List.of(
                OrderMenu.from("티본스테이크", 10),
                OrderMenu.from("바비큐립", 11)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Order.from(orderMenus));
    }

    @Test
    void 음료만_주문하면_예외가_발생한다() {
        List<OrderMenu> orderMenus = List.of(
                OrderMenu.from("제로콜라", 1),
                OrderMenu.from("레드와인", 1),
                OrderMenu.from("샴페인", 1)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Order.from(orderMenus));
    }
}

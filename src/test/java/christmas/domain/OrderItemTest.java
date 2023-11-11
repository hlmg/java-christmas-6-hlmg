package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class OrderItemTest {

    @ParameterizedTest
    @ValueSource(strings = {"김밥, 새우튀김"})
    void 메뉴판에_없는_메뉴면_예외가_발생한다(String menuName) {

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderItem.from(menuName, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void 수량이_1_이상이_아니면_예외가_발생한다(int quantity) {

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> OrderItem.from("타파스", quantity));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            타파스, 1, 5500
            타파스, 2, 11000
            """)
    void 가격을_계산할_수_있다(String menuName, int quantity, int expected) {
        OrderItem orderItem = OrderItem.from(menuName, quantity);

        int actual = orderItem.getPrice();

        assertThat(actual).isEqualTo(expected);
    }
}

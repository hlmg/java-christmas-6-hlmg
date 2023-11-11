package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
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
}

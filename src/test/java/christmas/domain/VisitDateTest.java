package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class VisitDateTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void 방문_날짜가_유효하지_않으면_예외가_발생한다(int dayOfMonth) {

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> VisitDate.from(dayOfMonth));
    }
}

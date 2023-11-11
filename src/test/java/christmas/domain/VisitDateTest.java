package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
class VisitDateTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void 방문_날짜가_유효하지_않으면_예외가_발생한다(int dayOfMonth) {

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> VisitDate.from(dayOfMonth));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            2023-12-01, 2023-12-02, false
            2023-12-04, 2023-12-05, true
            2023-12-05, 2023-12-06, true
            2023-12-06, 2023-12-07, false
            2023-12-10, 2023-12-01, false
            """)
    void 방문_날짜가_기간_안에_있는지_확인할_수_있다(String startDate, String endDate, boolean expected) {
        VisitDate visitDate = VisitDate.from(5);

        boolean actual = visitDate.isIn(LocalDate.parse(startDate), LocalDate.parse(endDate));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            2023-12-01, 1, 0
            2023-12-01, 3, 2
            2023-12-03, 1, -2
            """)
    void 기준일로부터의_일수를_계산할_수_있다(String baseDate, int dayOfMonth, int expected) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);

        int actual = visitDate.getDaysFrom(LocalDate.parse(baseDate));

        assertThat(actual).isEqualTo(expected);
    }
}

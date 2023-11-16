package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class EventBadgeTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            20000, 산타
            19999, 트리
            10000, 트리
            9999, 별
            5000, 별
            4999, 없음
            0, 없음
            """)
    void 총혜택_금액으로_이벤트_배지를_생성할_수_있다(int totalBenefitAmount, String expected) {

        EventBadge eventBadge = EventBadge.from(totalBenefitAmount);

        assertThat(eventBadge.getName()).isEqualTo(expected);
    }
}

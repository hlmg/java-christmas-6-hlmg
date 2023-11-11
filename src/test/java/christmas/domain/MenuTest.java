package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
class MenuTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            양송이수프, true
            김밥, false
            """)
    void 메뉴_이름으로_메뉴를_찾을_수_있다(String menuName, boolean expected) {
        Optional<Menu> menu = Menu.from(menuName);

        assertThat(menu.isPresent()).isEqualTo(expected);
    }
}

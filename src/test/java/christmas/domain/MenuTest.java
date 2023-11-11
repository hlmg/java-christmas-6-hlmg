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

    @ParameterizedTest
    @CsvSource(textBlock = """
            양송이수프, false
            타파스, false
            초코케이크, true
            아이스크림, true
            """)
    void 메뉴가_디저트인지_확인할_수_있다(String menuName, boolean expected) {
        Menu menu = Menu.from(menuName).orElseThrow();

        assertThat(menu.isDessert()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            양송이수프, false
            티본스테이크, true
            바비큐립, true
            해산물파스타, true
            크리스마스파스타, true
            초코케이크, false
            아이스크림, false
            """)
    void 메뉴가_메인인지_확인할_수_있다(String menuName, boolean expected) {
        Menu menu = Menu.from(menuName).orElseThrow();

        assertThat(menu.isMain()).isEqualTo(expected);
    }
}

package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.dto.OrderItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    public int getVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public List<OrderItemRequestDto> getOrderItems() {
        List<OrderItemRequestDto> orderItemRequestDtos = new ArrayList<>();
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        Pattern pattern = Pattern.compile("^([가-힣a-zA-Z]+-[0-9]+,?)+$");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        String[] split = input.split(",");
        for (String s : split) {
            String[] split1 = s.split("-");
            orderItemRequestDtos.add(new OrderItemRequestDto(split1[0], Integer.parseInt(split1[1])));
        }
        return orderItemRequestDtos;
    }
}

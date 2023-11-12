package christmas.view;

public class OutputView {

    public void printWelcome() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreview(int dayOfMonth) {
        String message = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", dayOfMonth);
        System.out.println(message);
    }
}

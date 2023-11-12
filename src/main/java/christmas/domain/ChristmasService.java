package christmas.domain;

public class ChristmasService {

    //TODO: test 작성
    public String getEventBadge(int totalBenefitAmount) {
        if (totalBenefitAmount >= 20000) {
            return "산타";
        } else if (totalBenefitAmount >= 10000) {
            return "트리";
        } else if (totalBenefitAmount >= 5000) {
            return "별";
        }
        return "없음";
    }
}

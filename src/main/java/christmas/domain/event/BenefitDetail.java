package christmas.domain.event;

public class BenefitDetail {
    private final Event event;
    private final Benefit benefit;

    private BenefitDetail(Event event, Benefit benefit) {
        this.event = event;
        this.benefit = benefit;
    }

    public static BenefitDetail from(Event event, Benefit benefit) {
        return new BenefitDetail(event, benefit);
    }
}

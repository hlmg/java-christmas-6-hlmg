package christmas.domain;

import java.time.LocalDate;

public class Plan {
    private final VisitDate visitDate;
    private final Order order;

    private Plan(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
    }

    public static Plan from(VisitDate visitDate, Order order) {
        return new Plan(visitDate, order);
    }

    public boolean isIn(LocalDate startDate, LocalDate endDate) {
        return visitDate.isIn(startDate, endDate);
    }
}

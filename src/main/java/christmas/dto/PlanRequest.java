package christmas.dto;

import christmas.domain.Order;
import christmas.domain.VisitDate;

public record PlanRequest(VisitDate visitDate, Order order) {
}

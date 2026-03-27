package com.nkediya.test.bookmyshow.pricing.PricingStrategy;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class AfternoonDiscountStrategy implements PricingStrategy {
    @Override
    @Order(1)
    public double apply(Show show, List<Integer> seats, double currentTotal) {

        if (isAfternoon(show)) {
            return currentTotal * 0.8;
        }

        return currentTotal;
    }

    private boolean isAfternoon(Show show) {
        return show.getStartTime().isAfter(LocalTime.NOON)
                && show.getStartTime().isBefore(LocalTime.of(16, 0));
    }
}

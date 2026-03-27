package com.nkediya.bookmyshow.pricing.strategy;

import com.nkediya.bookmyshow.show.domain.Show;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ThirdTicketPricingStrategy implements PricingStrategy {

    private static final double BASE_PRICE = 200;

    @Override
    @Order(1)
    public double apply(Show show, List<Integer> seats, double currentTotal) {

        if (seats.size() >= 3) {
            return currentTotal - (BASE_PRICE * 0.5);
        }

        return currentTotal;
    }
}

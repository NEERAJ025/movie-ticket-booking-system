package com.nkediya.test.bookmyshow.pricing.PricingStrategy;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ThirdTicketDiscountStrategy implements PricingStrategy {

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

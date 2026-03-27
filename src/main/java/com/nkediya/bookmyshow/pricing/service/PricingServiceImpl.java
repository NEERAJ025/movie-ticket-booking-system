package com.nkediya.bookmyshow.pricing.service;

import com.nkediya.bookmyshow.pricing.strategy.PricingStrategy;
import com.nkediya.bookmyshow.show.domain.Show;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    private static final double BASE_PRICE = 200;

    private final List<PricingStrategy> strategies;

    PricingServiceImpl(List<PricingStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public double calculateTotalPrice(Show show, List<Integer> seats) {

        double total = BASE_PRICE * seats.size();

        for (PricingStrategy strategy : strategies) {
            total = strategy.apply(show, seats, total);
        }

        return total;
    }
}

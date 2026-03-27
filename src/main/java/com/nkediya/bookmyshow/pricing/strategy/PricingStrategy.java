package com.nkediya.bookmyshow.pricing.strategy;

import com.nkediya.bookmyshow.show.domain.Show;

import java.util.List;

public interface PricingStrategy {
        double apply(Show show, List<Integer> seats, double currentTotal);
}

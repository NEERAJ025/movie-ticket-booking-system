package com.nkediya.test.bookmyshow.pricing.PricingStrategy;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;

import java.util.List;

public interface PricingStrategy {
        double apply(Show show, List<Integer> seats, double currentTotal);
}

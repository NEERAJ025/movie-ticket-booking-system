package com.nkediya.bookmyshow.pricing.PricingStrategy;

import com.nkediya.bookmyshow.show.ShowDomain.Show;

import java.util.List;

public interface PricingStrategy {
        double apply(Show show, List<Integer> seats, double currentTotal);
}

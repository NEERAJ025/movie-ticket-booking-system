package com.nkediya.test.bookmyshow.pricing.PricingStrategy;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;

import java.util.List;

public class WeekdaysDiscountStrategy implements PricingStrategy{

    @Override
    public double apply(Show show, List<Integer> seats, double currentTotal) {
        return 0;
    }
}

package com.nkediya.test.bookmyshow.pricing.PricingService;

import com.nkediya.test.bookmyshow.show.ShowDomain.Show;

import java.util.List;

public interface PricingService {
    double calculateTotalPrice(Show show, List<Integer> seats);
}

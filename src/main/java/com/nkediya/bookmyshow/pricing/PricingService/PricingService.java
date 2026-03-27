package com.nkediya.bookmyshow.pricing.PricingService;

import com.nkediya.bookmyshow.show.ShowDomain.Show;

import java.util.List;

public interface PricingService {
    double calculateTotalPrice(Show show, List<Integer> seats);
}

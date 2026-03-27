package com.nkediya.bookmyshow.pricing.service;

import com.nkediya.bookmyshow.show.domain.Show;

import java.util.List;

public interface PricingService {
    double calculateTotalPrice(Show show, List<Integer> seats);
}

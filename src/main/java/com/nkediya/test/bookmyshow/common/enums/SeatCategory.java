package com.nkediya.test.bookmyshow.common.enums;

public enum SeatCategory {
    SILVER(200),
    GOLD(300),
    PLATINUM(500);

    private final double price;

    SeatCategory(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

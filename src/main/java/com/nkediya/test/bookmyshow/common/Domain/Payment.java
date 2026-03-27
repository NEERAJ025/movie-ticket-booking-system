package com.nkediya.test.bookmyshow.common.Domain;

import com.nkediya.test.bookmyshow.common.enums.PaymentStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Payment {
    private final UUID paymentId;
    private final PaymentStatus status;
    private double amount;


    public Payment(PaymentStatus status, double amount) {
        this.paymentId = UUID.randomUUID();
        this.amount = amount;
        this.status = status;
    }

}

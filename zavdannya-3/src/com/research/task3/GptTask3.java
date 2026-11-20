package com.research.task3;

import com.research.model.BankTransfer;
import com.research.model.CardPayment;
import com.research.model.CryptoPayment;
import com.research.model.Payment;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GptTask3 {

    public static BigDecimal calculateFee(Payment payment) {
        BigDecimal fee = switch (payment) {
            case CardPayment p when p.isInternational() ->
                    p.amount().multiply(new BigDecimal("0.03"));

            case CardPayment p ->
                    p.amount().multiply(new BigDecimal("0.015"));

            case CryptoPayment p when p.network().equalsIgnoreCase("TRC20") ->
                    new BigDecimal("1.00");

            case CryptoPayment p ->
                    new BigDecimal("5.00");

            case BankTransfer p when p.amount().compareTo(new BigDecimal("10000")) >= 0 ->
                    BigDecimal.ZERO;

            case BankTransfer p ->
                    new BigDecimal("25.00");
        };

        return fee.setScale(2, RoundingMode.HALF_UP);
    }
}
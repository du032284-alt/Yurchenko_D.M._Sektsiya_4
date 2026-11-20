package com.research.task3;

import com.research.model.BankTransfer;
import com.research.model.CardPayment;
import com.research.model.CryptoPayment;
import com.research.model.Payment;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeminiTask3 {

    public BigDecimal calculateFee(Payment payment) {
        BigDecimal fee = switch (payment) {
            case CardPayment(var amount, var isInternational) when isInternational ->
                    amount.multiply(new BigDecimal("0.03"));
            case CardPayment(var amount, var isInternational) ->
                    amount.multiply(new BigDecimal("0.015"));
            case CryptoPayment(var amount, var network) when "TRC20".equalsIgnoreCase(network) ->
                    new BigDecimal("1.00");
            case CryptoPayment(var amount, var network) ->
                    new BigDecimal("5.00");
            case BankTransfer(var amount) when amount.compareTo(new BigDecimal("10000")) >= 0 ->
                    BigDecimal.ZERO;
            case BankTransfer(var amount) ->
                    new BigDecimal("25.00");
        };

        return fee.setScale(2, RoundingMode.HALF_UP);
    }
}
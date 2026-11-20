package com.research.task3;

import com.research.model.BankTransfer;
import com.research.model.CardPayment;
import com.research.model.CryptoPayment;
import com.research.model.Payment;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ClaudeTask3 {

    public static BigDecimal calculateFee(Payment payment) {
        return switch (payment) {
            case CardPayment c when c.isInternational() ->
                    c.amount().multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
            case CardPayment c ->
                    c.amount().multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
            case CryptoPayment c when "TRC20".equalsIgnoreCase(c.network()) ->
                    new BigDecimal("1.00");
            case CryptoPayment c ->
                    new BigDecimal("5.00");
            case BankTransfer b when b.amount().compareTo(new BigDecimal("10000")) >= 0 ->
                    new BigDecimal("0.00");
            case BankTransfer b ->
                    new BigDecimal("25.00");
        };
    }
}
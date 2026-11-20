package com.research.task3;

import com.research.model.BankTransfer;
import com.research.model.CardPayment;
import com.research.model.CryptoPayment;
import com.research.model.Payment;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CopilotTask3 {

    public static BigDecimal calculateFee(Payment payment) {
        return switch (payment) {
            case CardPayment(BigDecimal amount, boolean isInternational) when isInternational ->
                    amount.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);

            case CardPayment(BigDecimal amount, boolean isInternational) ->
                    amount.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);

            case CryptoPayment(BigDecimal amount, String network)
                    when "TRC20".equalsIgnoreCase(network) ->
                    new BigDecimal("1.00").setScale(2, RoundingMode.HALF_UP);

            case CryptoPayment(BigDecimal amount, String network) ->
                    new BigDecimal("5.00").setScale(2, RoundingMode.HALF_UP);

            case BankTransfer(BigDecimal amount)
                    when amount.compareTo(new BigDecimal("10000")) >= 0 ->
                    BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

            case BankTransfer(BigDecimal amount) ->
                    new BigDecimal("25.00").setScale(2, RoundingMode.HALF_UP);
        };
    }
}
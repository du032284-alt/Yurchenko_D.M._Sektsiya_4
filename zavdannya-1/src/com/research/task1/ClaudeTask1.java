package com.research.task1;

import com.research.model.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClaudeTask1 {

    public static Map<String, BigDecimal> averageAmountByCategory(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate from = today.minusDays(30);

        return transactions.stream()
                .filter(Transaction::isSuccessful)
                .filter(t -> !t.date().isBefore(from) && !t.date().isAfter(today))
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .map(Transaction::amount)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                                        .divide(BigDecimal.valueOf(list.size()), 2, RoundingMode.HALF_UP)
                        )
                ));
    }
}
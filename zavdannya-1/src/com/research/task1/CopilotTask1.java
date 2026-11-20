package com.research.task1;

import com.research.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CopilotTask1 {

    public static Map<String, Double> averageAmountByCategory(List<Transaction> transactions) {
        LocalDate cutoffDate = LocalDate.now().minusDays(30);

        return transactions.stream()
                .filter(Transaction::isSuccessful)
                .filter(t -> !t.date().isBefore(cutoffDate))
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.averagingDouble(t -> t.amount().doubleValue())
                ));
    }
}
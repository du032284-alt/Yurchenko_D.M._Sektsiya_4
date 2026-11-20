package com.research.task1;

import com.research.model.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeminiTask1 { // або CopilotTask1 — залежно від того, чий це файл

    public Map<String, BigDecimal> calculateAverageSuccessfulAmountByCategory(List<Transaction> transactions) {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        return transactions.stream()
                .filter(Transaction::isSuccessful)
                .filter(t -> !t.date().isBefore(thirtyDaysAgo))
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

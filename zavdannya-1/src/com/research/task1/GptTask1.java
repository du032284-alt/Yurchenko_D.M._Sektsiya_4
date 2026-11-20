package com.research.task1;

import com.research.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GptTask1 {

    public static Map<String, BigDecimal> calculateAverageByCategory(List<Transaction> transactions) {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minusDays(30);

        return Map.of();
    }
}
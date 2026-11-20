package com.research.task1;

import com.research.model.Transaction;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Task1BenchmarkTest {

    @Test
    void testGeminiAndCopilot() {
        LocalDate today = LocalDate.now();
        List<Transaction> transactions = List.of(
                new Transaction("1", new BigDecimal("100.00"), today.minusDays(5), true, "Food"),
                new Transaction("2", new BigDecimal("200.00"), today.minusDays(30), true, "Food"),
                new Transaction("3", new BigDecimal("500.00"), today.minusDays(31), true, "Food"),
                new Transaction("4", new BigDecimal("300.00"), today.minusDays(2), false, "Food"),
                new Transaction("5", new BigDecimal("150.00"), today.minusDays(10), true, "Transport")
        );

        // Перевірка Google Gemini
        var geminiResult = new GeminiTask1().calculateAverageSuccessfulAmountByCategory(transactions);
        assertEquals(2, geminiResult.size());
        assertEquals(new BigDecimal("150.00"), geminiResult.get("Food"));
        assertEquals(new BigDecimal("150.00"), geminiResult.get("Transport"));

        // Перевірка GitHub Copilot
        var copilotResult = CopilotTask1.averageAmountByCategory(transactions);
        assertEquals(2, copilotResult.size());
        assertEquals(150.0, copilotResult.get("Food"), 0.01);
        assertEquals(150.0, copilotResult.get("Transport"), 0.01);
    }
}
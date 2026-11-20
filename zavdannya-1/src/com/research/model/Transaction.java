package com.research.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(
        String id,
        BigDecimal amount,
        LocalDate date,
        boolean isSuccessful,
        String category
) {}
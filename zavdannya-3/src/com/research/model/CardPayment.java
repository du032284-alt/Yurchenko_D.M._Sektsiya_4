package com.research.model;

import java.math.BigDecimal;

public record CardPayment(BigDecimal amount, boolean isInternational) implements Payment {}
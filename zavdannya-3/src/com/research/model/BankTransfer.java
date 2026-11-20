package com.research.model;

import java.math.BigDecimal;

public record BankTransfer(BigDecimal amount) implements Payment {}
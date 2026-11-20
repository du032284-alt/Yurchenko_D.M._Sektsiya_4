package com.research.model;

import java.math.BigDecimal;

public record CryptoPayment(BigDecimal amount, String network) implements Payment {}
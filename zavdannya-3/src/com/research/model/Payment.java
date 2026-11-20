package com.research.model;

public sealed interface Payment permits CardPayment, CryptoPayment, BankTransfer {}
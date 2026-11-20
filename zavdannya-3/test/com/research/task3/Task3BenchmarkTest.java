package com.research.task3;

import com.research.model.BankTransfer;
import com.research.model.CardPayment;
import com.research.model.CryptoPayment;
import com.research.model.Payment;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class Task3BenchmarkTest {

    @Test
    public void testAllModels() {
        Payment cardIntl = new CardPayment(new BigDecimal("100.00"), true);       // 3.00
        Payment cardLocal = new CardPayment(new BigDecimal("100.00"), false);     // 1.50
        Payment cryptoTrc = new CryptoPayment(new BigDecimal("50.00"), "trc20");  // 1.00 (нижній регістр)
        Payment cryptoErc = new CryptoPayment(new BigDecimal("50.00"), "ERC20");  // 5.00
        Payment bankBig = new BankTransfer(new BigDecimal("10000.00"));           // 0.00 (межа 10000)
        Payment bankSmall = new BankTransfer(new BigDecimal("9999.99"));          // 25.00

        GeminiTask3 gemini = new GeminiTask3();

        // 1. ChatGPT
        assertEquals(new BigDecimal("3.00"), GptTask3.calculateFee(cardIntl));
        assertEquals(new BigDecimal("1.50"), GptTask3.calculateFee(cardLocal));
        assertEquals(new BigDecimal("1.00"), GptTask3.calculateFee(cryptoTrc));
        assertEquals(new BigDecimal("5.00"), GptTask3.calculateFee(cryptoErc));
        assertEquals(new BigDecimal("0.00"), GptTask3.calculateFee(bankBig));
        assertEquals(new BigDecimal("25.00"), GptTask3.calculateFee(bankSmall));

        // 2. Google Gemini
        assertEquals(new BigDecimal("3.00"), gemini.calculateFee(cardIntl));
        assertEquals(new BigDecimal("1.50"), gemini.calculateFee(cardLocal));
        assertEquals(new BigDecimal("1.00"), gemini.calculateFee(cryptoTrc));
        assertEquals(new BigDecimal("5.00"), gemini.calculateFee(cryptoErc));
        assertEquals(new BigDecimal("0.00"), gemini.calculateFee(bankBig));
        assertEquals(new BigDecimal("25.00"), gemini.calculateFee(bankSmall));

        // 3. GitHub Copilot
        assertEquals(new BigDecimal("3.00"), CopilotTask3.calculateFee(cardIntl));
        assertEquals(new BigDecimal("1.50"), CopilotTask3.calculateFee(cardLocal));
        assertEquals(new BigDecimal("1.00"), CopilotTask3.calculateFee(cryptoTrc));
        assertEquals(new BigDecimal("5.00"), CopilotTask3.calculateFee(cryptoErc));
        assertEquals(new BigDecimal("0.00"), CopilotTask3.calculateFee(bankBig));
        assertEquals(new BigDecimal("25.00"), CopilotTask3.calculateFee(bankSmall));
    }
}
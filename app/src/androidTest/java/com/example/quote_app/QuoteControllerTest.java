package com.example.quote_app;

import junit.framework.TestCase;

public class QuoteControllerTest extends TestCase {

    public void testGetQuote() {
        QuoteController qc = new QuoteController();
        qc.regenerateQuote();

        String quote = qc.getQuote();

        assertNotNull(quote);
    }
}
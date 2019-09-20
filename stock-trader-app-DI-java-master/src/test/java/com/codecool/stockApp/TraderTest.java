package com.codecool.stockApp;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// TODO
class TraderTest {


    @Test // Bid was lower than price, buy should return false.
    void testBidLowerThanPrice() throws IOException {
        StockAPIService stockAPIService = mock(StockAPIService.class);
        Logger logger = mock(Logger.class);
        Trader trader = new Trader(logger, stockAPIService);
        when(stockAPIService.getPrice("aapl")).thenReturn(300.0);
        assertFalse(trader.buy("aapl", 250.0));
    }

    @Test // bid was equal or higher than price, buy() should return true.
    void testBidHigherThanPrice() throws IOException {
        StockAPIService stockAPIService = mock(StockAPIService.class);
        Logger logger = mock(Logger.class);
        Trader trader = new Trader(logger, stockAPIService);
        when(stockAPIService.getPrice("aapl")).thenReturn(300.0);
        assertTrue(trader.buy("aapl", 350.0));
    }
}
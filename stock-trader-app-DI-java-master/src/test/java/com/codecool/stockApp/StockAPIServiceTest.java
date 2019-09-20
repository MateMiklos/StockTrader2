package com.codecool.stockApp;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// TODO
class StockAPIServiceTest {

    private static final String url = String.format("https://financialmodelingprep.com/api/v3/stock/real-time-price/%s", "aapl");

    @Test // everything works
    void testGetPriceNormalValues() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenReturn("{\"price\":20}");
        assertEquals(20.0, stockAPIService.getPrice("aapl"));
    }

    @Test // readFromURL threw an exception
    void testGetPriceServerDown() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenThrow(IOException.class);
        assertThrows(IOException.class, () -> stockAPIService.getPrice("aapl"));
    }

    @Test // readFromURL returned wrong JSON
    void testGetPriceMalformedResponse() throws IOException {
        RemoteURLReader remoteURLReader = mock(RemoteURLReader.class);
        StockAPIService stockAPIService = new StockAPIService(remoteURLReader);
        when(remoteURLReader.readFromUrl(url)).thenReturn("{\"rice\":42}");
        assertThrows(JSONException.class, () -> stockAPIService.getPrice("aapl"));
    }

}
package com.mercadohoy.mercadohoybackend.crypto.dto;

/**
 * DTOs para mapear la respuesta de Binance /api/v3/ticker/24hr
 */
public class BinanceDtos {

    public static class BinanceTicker24h {

        private String symbol;              // BTCUSDT, ETHUSDT, etc.
        private String lastPrice;           // precio actual
        private String priceChangePercent;  // % cambio 24h

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(String lastPrice) {
            this.lastPrice = lastPrice;
        }

        public String getPriceChangePercent() {
            return priceChangePercent;
        }

        public void setPriceChangePercent(String priceChangePercent) {
            this.priceChangePercent = priceChangePercent;
        }
    }
}

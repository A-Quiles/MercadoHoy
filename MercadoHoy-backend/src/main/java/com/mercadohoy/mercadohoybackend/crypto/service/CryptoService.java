package com.mercadohoy.mercadohoybackend.crypto.service;

import com.mercadohoy.mercadohoybackend.crypto.dto.BinanceDtos;
import com.mercadohoy.mercadohoybackend.crypto.dto.CryptoAsset;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que consulta la API pública de Binance.
 */
@Service
public class CryptoService {

    private final WebClient webClient;

    public CryptoService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.binance.com")
                .build();
    }

    /**
     * Obtiene una cripto por símbolo "humano" (BTC, ETH, DOGE, etc.).
     * Internamente consulta el par contra USDT: BTCUSDT, ETHUSDT...
     */
    public CryptoAsset getAssetBySymbol(String symbol) {
        if (symbol == null || symbol.isBlank()) {
            return null;
        }

        String upper = symbol.toUpperCase();
        String pair = upper + "USDT";  // BTC -> BTCUSDT

        Mono<BinanceDtos.BinanceTicker24h> mono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v3/ticker/24hr")
                        .queryParam("symbol", pair)
                        .build())
                .retrieve()
                .bodyToMono(BinanceDtos.BinanceTicker24h.class);

        BinanceDtos.BinanceTicker24h ticker = mono.block();
        if (ticker == null) {
            return null;
        }

        double price = parseDoubleSafe(ticker.getLastPrice());
        double change = parseDoubleSafe(ticker.getPriceChangePercent());

        // Por ahora usamos symbol como "nombre"
        return new CryptoAsset(
                pair,           // id
                upper,          // symbol
                upper,          // name
                price,
                change
        );
    }

    /**
     * Obtiene varias criptos a la vez, pasando símbolos separados.
     * Ej: ["BTC", "ETH", "DOGE"]
     */
    public List<CryptoAsset> getAssetsBySymbols(List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            return List.of();
        }

        List<CryptoAsset> result = new ArrayList<>();
        for (String s : symbols) {
            CryptoAsset asset = getAssetBySymbol(s);
            if (asset != null) {
                result.add(asset);
            }
        }
        return result;
    }

    private double parseDoubleSafe(String value) {
        if (value == null) return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }
}

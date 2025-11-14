package com.mercadohoy.mercadohoybackend.crypto.controller;

import com.mercadohoy.mercadohoybackend.crypto.dto.CryptoAsset;
import com.mercadohoy.mercadohoybackend.crypto.service.CryptoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    /**
     * GET /api/crypto/asset/{symbol}
     * Ejemplos:
     *  /api/crypto/asset/BTC
     *  /api/crypto/asset/ETH
     */
    @GetMapping("/asset/{symbol}")
    public ResponseEntity<CryptoAsset> getAsset(@PathVariable String symbol) {
        CryptoAsset asset = cryptoService.getAssetBySymbol(symbol);
        if (asset == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset);
    }

    /**
     * GET /api/crypto/assets?symbols=BTC,ETH,DOGE
     */
    @GetMapping("/assets")
    public ResponseEntity<List<CryptoAsset>> getAssets(@RequestParam String symbols) {
        List<String> symbolList = Arrays.stream(symbols.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        List<CryptoAsset> result = cryptoService.getAssetsBySymbols(symbolList);
        return ResponseEntity.ok(result);
    }
}

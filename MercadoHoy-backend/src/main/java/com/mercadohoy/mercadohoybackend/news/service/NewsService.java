package com.mercadohoy.mercadohoybackend.news.service;

import com.mercadohoy.mercadohoybackend.news.dto.NewsDtos;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    public NewsDtos.GenerateNewsResponse generateNews(NewsDtos.GenerateNewsRequest request) {

        String dummy = String.format(
                "[BORRADOR IA - DEMO]%n" +
                "Tema: %s%n" +
                "Idioma: %s%n" +
                "Tono: %s%n" +
                "Máx. palabras: %d%n%n" +
                "Aquí iría el contenido generado por la IA según el tema y parámetros indicados.",
                request.getTopic(),
                request.getLanguage(),
                request.getTone(),
                request.getMaxWords()
        );

        return new NewsDtos.GenerateNewsResponse(dummy);
    }
}

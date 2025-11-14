package com.mercadohoy.mercadohoybackend.news.controller;

import com.mercadohoy.mercadohoybackend.news.dto.NewsDtos;
import com.mercadohoy.mercadohoybackend.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/generate")
    public ResponseEntity<NewsDtos.GenerateNewsResponse> generateNews(
            @RequestBody NewsDtos.GenerateNewsRequest request) {

        NewsDtos.GenerateNewsResponse response = newsService.generateNews(request);
        return ResponseEntity.ok(response);
    }
}

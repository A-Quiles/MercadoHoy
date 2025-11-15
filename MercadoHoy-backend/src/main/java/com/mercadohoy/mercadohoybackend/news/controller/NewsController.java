package com.mercadohoy.mercadohoybackend.news.controller;

import com.mercadohoy.mercadohoybackend.news.dto.NewsDtos;
import com.mercadohoy.mercadohoybackend.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // GET /api/news → todas las noticias activas
    @GetMapping
    public List<NewsDtos> listarActivas() {
        return newsService.listarActivas();
    }

    // GET /api/news/categoria/{categoria}
    @GetMapping("/categoria/{categoria}")
    public List<NewsDtos> listarPorCategoria(@PathVariable String categoria) {
        return newsService.listarActivasPorCategoria(categoria);
    }

    // GET /api/news/{id}
    @GetMapping("/{id}")
    public ResponseEntity<NewsDtos> obtenerPorId(@PathVariable String id) {
        return newsService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/news
    @PostMapping
    public NewsDtos crear(@RequestBody NewsDtos noticia) {
        return newsService.crear(noticia);
    }

    // PUT /api/news/{id}
    @PutMapping("/{id}")
    public NewsDtos actualizar(@PathVariable String id, @RequestBody NewsDtos noticia) {
        return newsService.actualizar(id, noticia);
    }

    // DELETE /api/news/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        newsService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

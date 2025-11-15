package com.mercadohoy.mercadohoybackend.news.repository;

import com.mercadohoy.mercadohoybackend.news.model.Noticia;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoticiaRepository extends MongoRepository<Noticia, String> {

    // Todas las noticias activas ordenadas por fecha desc
    List<Noticia> findByActivoTrueOrderByFechaDesc();

    // Noticias activas por categoría
    List<Noticia> findByCategoriaAndActivoTrueOrderByFechaDesc(String categoria);
}

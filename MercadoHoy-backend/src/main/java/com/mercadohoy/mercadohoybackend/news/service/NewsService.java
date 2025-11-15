package com.mercadohoy.mercadohoybackend.news.service;

import com.mercadohoy.mercadohoybackend.news.dto.NewsDtos;
import com.mercadohoy.mercadohoybackend.news.model.Noticia;
import com.mercadohoy.mercadohoybackend.news.repository.NoticiaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NoticiaRepository noticiaRepository;

    public NewsService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    // ===== Helpers de conversión =====

    private NewsDtos toDto(Noticia entity) {
        return new NewsDtos(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescripcion(),
                entity.getFuente(),
                entity.getCategoria(),
                entity.getActivo(),
                entity.getFecha()
        );
    }

    private Noticia toEntity(NewsDtos dto) {
        Noticia noticia = new Noticia();
        noticia.setId(dto.getId());
        noticia.setTitulo(dto.getTitulo());
        noticia.setDescripcion(dto.getDescripcion());
        noticia.setFuente(dto.getFuente());
        noticia.setCategoria(dto.getCategoria());
        noticia.setActivo(dto.getActivo());
        noticia.setFecha(dto.getFecha());
        return noticia;
    }

    // ===== Operaciones públicas =====

    public List<NewsDtos> listarActivas() {
        return noticiaRepository.findByActivoTrueOrderByFechaDesc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<NewsDtos> listarActivasPorCategoria(String categoria) {
        return noticiaRepository.findByCategoriaAndActivoTrueOrderByFechaDesc(categoria)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NewsDtos crear(NewsDtos dto) {
        if (dto.getFecha() == null) {
            dto.setFecha(new Date());
        }
        if (dto.getActivo() == null) {
            dto.setActivo(true);
        }
        Noticia saved = noticiaRepository.save(toEntity(dto));
        return toDto(saved);
    }

    public Optional<NewsDtos> obtenerPorId(String id) {
        return noticiaRepository.findById(id)
                .map(this::toDto);
    }

    public NewsDtos actualizar(String id, NewsDtos dto) {
        return noticiaRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(dto.getTitulo());
                    existing.setDescripcion(dto.getDescripcion());
                    existing.setFuente(dto.getFuente());
                    existing.setCategoria(dto.getCategoria());
                    existing.setActivo(dto.getActivo());
                    if (dto.getFecha() != null) {
                        existing.setFecha(dto.getFecha());
                    }
                    Noticia saved = noticiaRepository.save(existing);
                    return toDto(saved);
                })
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada con id: " + id));
    }

    public void eliminar(String id) {
        noticiaRepository.deleteById(id);
    }
}

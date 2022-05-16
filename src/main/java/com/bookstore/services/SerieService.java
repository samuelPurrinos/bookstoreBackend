package com.bookstore.services;

import com.bookstore.domain.Serie;
import java.util.List;
import java.util.Optional;

public interface SerieService {
    Optional<Serie> findSerieById(Long id);
    List<Serie> findAll();
    Serie updateSerie(Serie issue);
    boolean deleteSerie(Long id);
}

package com.bookstore.services;

import com.bookstore.domain.Serie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesServiceImpl implements SeriesService{

    @Override
    public Optional<Serie> findSerieById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Serie> findAll() {
        return null;
    }

    @Override
    public Serie updateSerie(Serie issue) {
        return null;
    }

    @Override
    public boolean deleteSerie(Long id) {
        return false;
    }
}

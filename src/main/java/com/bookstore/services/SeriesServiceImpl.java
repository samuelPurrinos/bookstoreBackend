package com.bookstore.services;

import com.bookstore.domain.Serie;
import com.bookstore.repositories.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService{
    private final SerieRepository serieRepository;
    @Override
    public Optional<Serie> findSerieById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Serie> findAll() {
        return serieRepository.findAll();
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

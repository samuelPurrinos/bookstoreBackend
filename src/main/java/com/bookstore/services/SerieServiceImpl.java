package com.bookstore.services;

import com.bookstore.domain.Serie;
import com.bookstore.repositories.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SerieServiceImpl implements SerieService {
    private final SerieRepository serieRepository;
    @Override
    public Optional<Serie> findSerieById(Long id) {
        return serieRepository.findById(id);
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
        if(serieRepository.existsById(id)){
            serieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

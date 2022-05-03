package com.bookstore.repositories;

import com.bookstore.domain.Serie;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SerieRepository extends CrudRepository<Serie, Long> {
    Optional<Serie> findByTitle(String title);
}
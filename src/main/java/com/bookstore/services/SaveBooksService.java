package com.bookstore.services;

import com.bookstore.domain.*;

import java.util.Optional;

public interface SaveBooksService {
    Optional<Serie> saveSerie(final Serie serie);
    Issue saveIssue(final Issue issue);
}

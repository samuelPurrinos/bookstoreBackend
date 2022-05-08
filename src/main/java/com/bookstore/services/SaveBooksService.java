package com.bookstore.services;

import com.bookstore.domain.*;

public interface SaveBooksService {
    Serie saveSerie(final Serie serie);
    Issue saveIssue(final Issue issue);
}

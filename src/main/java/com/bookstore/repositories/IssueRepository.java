package com.bookstore.repositories;

import com.bookstore.domain.Issue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    List<Issue> findAll();
}
package com.bookstore.services;

import com.bookstore.domain.Issue;

import java.util.List;
import java.util.Optional;

public interface IssuesService {
    Optional<Issue> findIssueById(Long id);
    List<Issue> findAll();
    Issue updateIssue(Issue issue);
    boolean deleteIssue(Long id);
}

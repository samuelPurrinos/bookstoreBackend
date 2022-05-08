package com.bookstore.services;

import com.bookstore.domain.Issue;

import java.util.List;
import java.util.Optional;

public class IssuesServiceImpl implements IssuesService{
    @Override
    public Optional<Issue> findIssueById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Issue> findAll() {
        return null;
    }

    @Override
    public Issue updateIssue(Issue issue) {
        return null;
    }

    @Override
    public boolean deleteIssue(Long id) {
        return false;
    }
}

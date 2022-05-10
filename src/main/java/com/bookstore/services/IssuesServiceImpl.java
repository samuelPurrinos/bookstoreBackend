package com.bookstore.services;

import com.bookstore.domain.Issue;
import com.bookstore.repositories.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class IssuesServiceImpl implements IssuesService{

    private final IssueRepository issueRepository;

    @Override
    public Optional<Issue> findIssueById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
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

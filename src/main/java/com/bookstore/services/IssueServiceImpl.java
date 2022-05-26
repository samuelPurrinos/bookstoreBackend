package com.bookstore.services;

import com.bookstore.domain.Issue;
import com.bookstore.repositories.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    @Override
    public Optional<Issue> findIssueById(Long id) {return issueRepository.findById(id);}

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
        if(issueRepository.existsById(id)){
            issueRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

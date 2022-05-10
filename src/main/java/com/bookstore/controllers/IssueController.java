package com.bookstore.controllers;

import com.bookstore.domain.Issue;
import com.bookstore.services.IssuesService;
import com.bookstore.services.SaveBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final SaveBooksService saveBooksService;
    private final IssuesService issuesService;

    @PostMapping("/save")
    public ResponseEntity<Issue> saveIssue(@RequestBody Issue newIssue){
        Issue savedIssue = saveBooksService.saveIssue(newIssue);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIssue);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getIssues() {
        return ResponseEntity.ok(issuesService.findAll());
    }

}

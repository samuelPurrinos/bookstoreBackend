package com.bookstore.controllers;

import com.bookstore.domain.Issue;
import com.bookstore.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final SaveBooksService saveBooksService;
    private final IssueService issueService;

    @PostMapping("/save")
    public ResponseEntity<Issue> saveIssue(@RequestBody @Valid Issue newIssue){
        Issue savedIssue = saveBooksService.saveIssue(newIssue);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIssue);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getIssues() {
        return ResponseEntity.ok(issueService.findAll());
    }

    @GetMapping(value ="{issueId}")
    public ResponseEntity<Optional<Issue>> getIssueById(@PathVariable Long issueId){
        return ResponseEntity.ok(issueService.findIssueById(issueId));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

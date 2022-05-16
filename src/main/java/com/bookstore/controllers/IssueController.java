package com.bookstore.controllers;

import com.bookstore.domain.Issue;
import com.bookstore.services.IssueService;
import com.bookstore.services.SaveBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

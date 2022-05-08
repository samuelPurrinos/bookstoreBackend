package com.bookstore.controllers;

import com.bookstore.domain.Issue;
import com.bookstore.services.SaveBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issues")
public class IssueController {

    private final SaveBooksService saveBooksService;

    @PostMapping("/save")
    public Issue saveIssue(@RequestBody Issue newIssue){
        return saveBooksService.saveIssue(newIssue);
    }

}

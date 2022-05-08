package com.bookstore.controllers;

import com.bookstore.domain.*;
import com.bookstore.services.SaveBooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private final SaveBooksService saveBooksService;

    @PostMapping("/save")
    public Serie saveSerie(@RequestBody Serie newSerie){
        return saveBooksService.saveSerie(newSerie);
    }
}

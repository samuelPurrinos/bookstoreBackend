package com.bookstore.controllers;

import com.bookstore.domain.*;
import com.bookstore.services.SaveBooksService;
import com.bookstore.services.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private final SaveBooksService saveBooksService;
    private final SeriesService seriesService;

    @PostMapping("/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie newSerie){
        Serie savedSerie = saveBooksService.saveSerie(newSerie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSerie);
    }

    @GetMapping
    public ResponseEntity<List<Serie>> getSeries(){
        return ResponseEntity.ok(seriesService.findAll());
    }
}

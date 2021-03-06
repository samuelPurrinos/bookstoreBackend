package com.bookstore.controllers;

import com.bookstore.domain.*;
import com.bookstore.services.SaveBooksService;
import com.bookstore.services.SerieService;
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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    private final SaveBooksService saveBooksService;
    private final SerieService serieService;

    @PostMapping("/save")
    public ResponseEntity<Serie> saveSerie(@RequestBody @Valid Serie newSerie){
        Serie savedSerie = saveBooksService.saveSerie(newSerie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSerie);
    }

    @GetMapping
    public ResponseEntity<List<Serie>> getSeries(){
        return ResponseEntity.ok(serieService.findAll());
    }

    @GetMapping(value = "/{serieId}")
    public ResponseEntity<Optional<Serie>> getSerieById(@PathVariable Long serieId) {
        return ResponseEntity.ok(serieService.findSerieById(serieId));
    }

    @DeleteMapping(value ="{serieId}")
    public ResponseEntity<Long> deleteSerie(@PathVariable Long serieId){
        boolean isRemoved =serieService.deleteSerie(serieId);
        if(isRemoved){
            return new ResponseEntity<>(serieId,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

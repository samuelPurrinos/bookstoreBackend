package com.bookstore.services;

import com.bookstore.domain.*;
import com.bookstore.repositories.IssueRepository;
import com.bookstore.repositories.SerieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SaveBooksServiceImpl implements SaveBooksService {

    private final IssueRepository issueRepository;
    private final SerieRepository serieRepository;

    @Override
    public Serie saveSerie(Serie serie) {
        //Check if the series exists for that title, otherwise save it
        Serie newSerie = serieRepository.findByTitle(serie.getTitle()).orElseGet(() -> {
            log.info("Saving a new serie with title {}", serie.getTitle());
            return serieRepository.save(serie);
        });
        return serieRepository.findByTitle(serie.getTitle()).get();
    }

    @Override
    public Issue saveIssue(Issue issue) {
        if(issue.getSeries()!=null){
        issue.setSeries(saveSerie(issue.getSeries()));}
        else {
            log.info("Cannot store a issue with a null series");
        }
        return issueRepository.save(issue);
    }
}

package com.bookstore.services;

import com.bookstore.domain.Issue;
import com.bookstore.domain.Serie;
import com.bookstore.repositories.IssueRepository;
import com.bookstore.repositories.SerieRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class SaveBooksServiceTest {
    @Mock
    private SaveBooksService saveBooksService;
    @Mock
    private SerieRepository serieRepository;
    @Mock
    private IssueRepository issueRepository;

    @BeforeEach
    public void setUp(){
        saveBooksService = new SaveBooksServiceImpl(issueRepository,serieRepository);
    }
    @Test
    public void CheckExistingSerieTest(){
        //given
        Serie existingSerie = new Serie(1L,"spiderman","marvel");
        given(serieRepository.findByTitle("spiderman")).willReturn(Optional.of(existingSerie));
        Issue newIssue = new Issue(1L,"isbn","Stan Lee",200.0,new Date(),new Serie(1L,"spiderman",",marvel")
        ,new byte[1024],new Date(),1,Issue.State.bad);
        //when
        Optional<Serie> storedSerie = serieRepository.findByTitle(existingSerie.getTitle());
        //then
        if(storedSerie.isPresent()){
        then(storedSerie.get().getTitle()).isEqualTo(newIssue.getSeries().getTitle());
        then(storedSerie.get().getTitle()).isEqualTo(existingSerie.getTitle());
        }
        verify(serieRepository,never()).save(existingSerie);

    }
}

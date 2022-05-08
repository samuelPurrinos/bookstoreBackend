package com.bookstore.controllers;

import com.bookstore.domain.*;
import com.bookstore.repositories.*;
import com.bookstore.services.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.*;
import java.util.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(SeriesController.class)
public class SeriesControllerTest {

    @MockBean
    private SaveBooksService saveBooksService;
    @MockBean
    private SerieRepository serieRepository;
    @Autowired
    MockMvc mvc;
    @Autowired
    private JacksonTester<Serie> jsonRequestSerie;
    @Autowired
    private JacksonTester<Serie> jsonResultSerie;
    @Autowired
    private JacksonTester<List<Serie>> jsonResultSerieList;
    private static Issue issue1,issue2,issue3;
    private static Serie record1,record2,record3;

    @BeforeEach
    void setup() {
        issue1 =Issue.builder().id(1L)
                .isbn("9781846539411").author("Stan Lee").price(200.0).orderDate(new Date()).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.excellent).build();
        record1 = Serie.builder().id(3l).title("Spiderman").publisher("Marvel").issues(List.of(issue1)).build();

        issue2 =Issue.builder().id(2L)
                .isbn("978-1563890505").author("Bob Kane").price(170.0).orderDate(new Date()).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.good).build();
        record2 = Serie.builder().id(3l).title("Batman").publisher("DC Comics").issues(List.of(issue2)).build();

        issue3 =Issue.builder().id(3L)
                .isbn("9788467483086").author("Akira Toriyama").price(280.0).orderDate(new Date()).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.excellent).build();
        record3 = Serie.builder().id(3L).title("Dr Slump").publisher("Planeta de Agostini").issues(List.of(issue3)).build();

    }

    @Test
    public void postValidSerie() throws Exception {
        //given
        given(saveBooksService.
                saveSerie(any()))
                .willReturn(record1);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/series/save").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestSerie.write(record1).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultSerie.write(
                        record1
                ).getJson());
    }

}

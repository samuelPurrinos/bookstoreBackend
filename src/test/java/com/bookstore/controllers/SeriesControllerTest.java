package com.bookstore.controllers;

import com.bookstore.domain.*;
import com.bookstore.repositories.*;
import com.bookstore.services.*;
import org.hamcrest.core.Is;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(SeriesController.class)
public class SeriesControllerTest {

    @MockBean
    private SaveBooksService saveBooksService;
    @MockBean
    SerieService serieService;
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
        record1 = Serie.builder().id(3L).title("Spiderman").publisher("Marvel").issues(List.of(issue1)).build();

        issue2 =Issue.builder().id(2L)
                .isbn("978-1563890505").author("Bob Kane").price(170.0).orderDate(new Date()).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.good).build();
        record2 = Serie.builder().id(3L).title("Batman").publisher("DC Comics").issues(List.of(issue2)).build();

        issue3 =Issue.builder().id(3L)
                .isbn("9788467483086").author("Akira Toriyama").price(280.0).orderDate(new Date()).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.excellent).build();
        record3 = Serie.builder().id(3L).title("Dr Slump").publisher("Planeta de Agostini").issues(List.of(issue3)).build();

    }

    @Test
    public void getSerieById_success() throws Exception {
        //given
        given(serieService.findSerieById(any())).willReturn(Optional.of(record1));

        //when
        MockHttpServletResponse response = mvc.perform(get("/series/1")).andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(200);
        then(response.getContentAsString()).isEqualTo(jsonResultSerie.write(record1).getJson());
    }

    @Test
    public void getSerieById_serieNotFound() throws Exception {
        //given
        given(serieService.findSerieById(any())).willReturn(Optional.empty());

        //when
        MockHttpServletResponse response = mvc.perform(get("/series/2")).andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(200);
        then(response.getContentAsString()).isEqualTo("null");
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
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultSerie.write(
                        record1
                ).getJson());
    }

    @Test
    public void postInvalidSerie() throws Exception {
        //given
        Serie invalidSerie = new Serie();

        //when
        MockHttpServletRequestBuilder mockRequest = post("/series/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequestSerie.write(invalidSerie).getJson());

        // then
        mvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", Is.is("Title is mandatory")))
                .andExpect(jsonPath("$.publisher", Is.is("Publisher is mandatory")));

    }
    @Test
    public void getAllSeries() throws Exception {

        //given
        given(serieService.findAll())
                .willReturn(List.of(record1,record2,record3));

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/series").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultSerieList.write(List.of(record1,record2,record3)
                ).getJson());

    }
}

package com.bookstore.controllers;

import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import com.bookstore.domain.*;
import com.bookstore.repositories.*;
import com.bookstore.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.*;
import java.util.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(IssueController.class)
@AutoConfigureJsonTesters
public class IssueControllerTest {

    @MockBean
    private SaveBooksService saveBooksService;
    @MockBean
    private IssuesService issuesService;
    @MockBean
    private IssueRepository issueRepository;
    @Autowired
    MockMvc mvc;
    @Autowired
    private JacksonTester<Issue> jsonRequestIssue;
    @Autowired
    private JacksonTester<Issue> jsonResultIssue;
    @Autowired
    private JacksonTester<List<Issue>> jsonResultIssueList;
    private static Issue record1,record2,record3;
    private static Serie serie1, serie2, serie3;

    @BeforeEach
    void setup() {
        serie1 = new Serie(1L,"spiderman","marvel");
        record1 =Issue.builder().id(1L)
                .isbn("9781846539411").author("Stan Lee").price(200.0).orderDate(new Date()).series(serie1).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.excellent).build();

        serie2 = new Serie(2L,"batman","dc comics");
        record2 =Issue.builder().id(2L)
                .isbn("978-1563890505").author("Bob Kane").price(170.0).orderDate(new Date()).series(serie2).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.good).build();

        serie3 = new Serie(3L,"Dr Slump","Planeta de Agostini");
        record3 =Issue.builder().id(3L)
                .isbn("9788467483086").author("Akira Toriyama").price(280.0).orderDate(new Date()).series(serie3).cover(new byte[1])
                .releaseDate(new Date()).volume(1).state(Issue.State.excellent).build();

    }

    @Test
    public void postValidIssue() throws Exception {

        given(saveBooksService.
                saveIssue(any()))
                .willReturn(record1);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/issues/save").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestIssue.write(record1).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultIssue.write(
                        record1
                ).getJson());

    }

    @Test
    public void getAllSeries() throws Exception {

        //given
        given(issuesService.findAll())
                .willReturn(List.of(record1,record2,record3));

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/issues").contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonResultIssueList.write(List.of(record1,record2,record3)
                ).getJson());

    }
}

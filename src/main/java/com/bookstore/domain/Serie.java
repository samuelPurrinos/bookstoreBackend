package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "publisher", length = 20, nullable = false)
    private String publisher;

    @OneToMany(mappedBy = "series",orphanRemoval = true)
    private List<Issue> issues= new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Issue> getIssues() {return issues;}

    public void setIssues(List<Issue> issues) {this.issues = issues;}
}
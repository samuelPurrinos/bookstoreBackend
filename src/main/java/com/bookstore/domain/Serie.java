package com.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "series")
@Getter
@Setter
@NoArgsConstructor
public class Serie {

    public Serie(Long id,String title, String publisher) {
        this.id=id;
        this.title = title;
        this.publisher=publisher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    @NotBlank
    private String title;

    @Column(name = "publisher", length = 20, nullable = false)
    @NotBlank
    private String publisher;

    @OneToMany(mappedBy = "series",orphanRemoval = true)
    private List<Issue> issues= new ArrayList<>();

}
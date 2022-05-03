package com.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.*;

@Entity
@Table(name = "issues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "isbn", length = 30, nullable = false)
    @NotBlank
    private String isbn;

    @Column(name = "author", length = 30, nullable = false)
    @NotBlank
    private String author;

    @Column(name = "price", precision = 10, nullable = false)
    @Positive
    @NotBlank
    private Double price;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", nullable = false)
    private Serie series;

    @Column(name = "cover", nullable = false)
    private byte[] cover;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(name = "volume", nullable = false)
    @Positive
    @NotBlank
    private Integer volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    public enum State { bad, broken, good, excellent}

}
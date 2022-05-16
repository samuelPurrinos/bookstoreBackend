package com.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "issues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "isbn", length = 30, nullable = false)
    @NotBlank(message = "isbn is mandatory")
    private String isbn;

    @Column(name = "author", length = 30, nullable = false)
    @NotBlank(message = "author is mandatory")
    private String author;

    @Column(name = "price", precision = 10, nullable = false)
    @Positive(message = "Price must be a positive number")
    @NotNull(message = "Price is mandatory")
    private Double price;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", nullable = false)
    private Serie series;

    @Column(name = "cover", nullable = false)
    private byte[] cover;

    @Column(name = "release_date", nullable = false)
    @NotNull(message = "Release date is mandatory")
    private Date releaseDate;

    @Column(name = "volume", nullable = false)
    @Positive(message = "Volume must be a positive number")
    @NotNull(message = "Volume is mandatory")
    private Integer volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    public enum State { bad, broken, good, excellent}

}
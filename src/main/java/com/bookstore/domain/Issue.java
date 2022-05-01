package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "issues")
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "isbn", length = 30, nullable = false)
    private String isbn;

    @Column(name = "author", length = 30, nullable = false)
    private String author;

    @Column(name = "price", precision = 10, nullable = false)
    private Double price;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", nullable = false)
    private Serie series;

    @Column(name = "cover", nullable = false)
    private byte[] cover;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private State state;

    public enum State { bad, broken, good, excellent}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return author;
    }

    public void setAutor(String autor) {
        this.author = autor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrecio(Double price) {
        this.price = price;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Serie getSerie() {
        return series;
    }

    public void setSerie(Serie serie) {
        this.series = serie;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public State getState() {
        return state;
    }

    public void setState(State estado) {
        this.state = estado;
    }

    public String getIsbn() {return isbn;}

    public void setIsbn(String isbn) {this.isbn = isbn;}
}
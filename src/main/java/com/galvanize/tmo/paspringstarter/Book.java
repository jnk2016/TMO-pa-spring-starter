package com.galvanize.tmo.paspringstarter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String author;
    private String title;
    private int datePublished;

    public int getDatePublished() {
        return datePublished;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDatePublished(int datePublished) {
        this.datePublished = datePublished;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

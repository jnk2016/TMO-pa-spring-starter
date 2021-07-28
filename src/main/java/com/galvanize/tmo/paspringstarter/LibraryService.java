package com.galvanize.tmo.paspringstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EntityManager entityManager;

    public Book addBook(HashMap<String, Object> body) {
        if(!(body.containsKey("author") && body.containsKey("title") && body.containsKey("datePublished") && body.size() == 3
                && body.get("datePublished").getClass() != String.class && body.get("author").getClass() == String.class && body.get("title").getClass() == String.class)) {
            throw new IllegalArgumentException("Incorrect formatting of request body!");
        }

        Book newBook = new Book();
        newBook.setAuthor((String)body.get("author"));
        newBook.setTitle((String)body.get("title"));
        newBook.setDatePublished((Integer)body.get("datePublished"));

        bookRepository.save(newBook);

        return newBook;
    }

    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getTitle));
        return books;
    }

    @Transactional
    public void deleteBooks() {
        long count = bookRepository.count();
        entityManager.createNativeQuery("TRUNCATE TABLE book RESTART IDENTITY").executeUpdate();
        if (count == 1) {
            bookRepository.save(new Book());
            entityManager.createNativeQuery("TRUNCATE TABLE book RESTART IDENTITY").executeUpdate();
        }
    }
}

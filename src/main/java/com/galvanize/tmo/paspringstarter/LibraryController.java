package com.galvanize.tmo.paspringstarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {
    @Autowired
    LibraryService libraryService;

//    @GetMapping("/health")
//    public void health() {
//
//    }
    @PostMapping
    public ResponseEntity<HashMap<String, Object>> addBook(@RequestBody HashMap<String, Object> body) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Book newBook = libraryService.addBook(body);
            response.put("id", newBook.getId());
            response.put("author", newBook.getAuthor());
            response.put("title", newBook.getTitle());
            response.put("datePublished", newBook.getDatePublished());

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getBooks() {
        HashMap<String, Object> response = new HashMap<>();
        try {
            List<Book> books = libraryService.getBooks();
            response.put("books", books);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBooks() {
        libraryService.deleteBooks();
        return ResponseEntity.noContent().build();
    }
}

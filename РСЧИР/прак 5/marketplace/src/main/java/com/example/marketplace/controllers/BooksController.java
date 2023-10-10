package com.example.marketplace.controllers;

import com.example.marketplace.models.Books;
import com.example.marketplace.models.Clients;
import com.example.marketplace.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping("")
    public ResponseEntity<List<Books>> getAllClients() {
        List<Books> books = booksRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable(value = "id") int bookId) {
        Optional<Books> book = booksRepository.findById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public Books createBook(@RequestBody Books book) {
        book.setTypeOfProduct("Book");
        return booksRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable(value = "id") int bookId,
                                           @RequestBody Books bookDetails) {
        Optional<Books> optionalBook = booksRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Books book = optionalBook.get();
            book.setAuthor(bookDetails.getAuthor());
            book.setNumberOfSeller(bookDetails.getNumberOfSeller());
            book.setTypeOfProduct("Book");
            book.setPrice(bookDetails.getPrice());
            book.setName(bookDetails.getName());
            return ResponseEntity.ok(booksRepository.save(book));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") int bookId) {
        Optional<Books> optionalBook = booksRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            booksRepository.delete(optionalBook.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

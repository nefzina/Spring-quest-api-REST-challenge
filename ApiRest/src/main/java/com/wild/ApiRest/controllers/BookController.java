package com.wild.ApiRest.controllers;

import com.wild.ApiRest.entity.Book;
import com.wild.ApiRest.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        return repository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> searchByKeyword(@RequestBody Map<String, String> body) {
        String keyWord = body.get("text");
        return repository.findByTitleContainingOrDescriptionContaining(keyWord, keyWord);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @PostMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookToUpdate = repository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable Long id) {
        repository.deleteById(id);
        return true;
    }

}
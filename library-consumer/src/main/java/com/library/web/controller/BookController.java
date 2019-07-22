package com.library.web.controller;

import com.library.dao.model.Book;
import com.library.dao.repository.BookRepository;
import com.library.web.exceptions.BookNotFoundException;
import com.library.web.exceptions.BookUnSupportedFieldPatchException;
import com.library.web.model.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    // Find
    @GetMapping()
    List<Book> findAll() {
        return repository.findAll();
    }

    // Save test
    @PostMapping()
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Find
    @GetMapping("/{id}")
    Book findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    x.setGenre(newBook.getGenre());
                    x.setQuantity(newBook.getQuantity());
                    x.setAvailable(newBook.getAvailable());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    // update author only
    @PatchMapping("/{id}")
    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/count")
    Count countBooks() {
        return new Count(repository.count());
    }

    @GetMapping("/user/{borrowerId}")
    List<Book> borrowerBooks(@PathVariable Long borrowerId) {
        return repository.findAllByBorrowerId(borrowerId);
    }

}

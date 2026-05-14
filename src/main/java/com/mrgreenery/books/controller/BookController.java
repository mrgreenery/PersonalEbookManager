package com.mrgreenery.books.controller;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping("/books")
  @ResponseStatus(HttpStatus.CREATED)
  public Book saveBook(@Valid @RequestBody Book book) {
    return bookService.saveBook(book);
  }

  @GetMapping("/books")
  public List<Book> fetchBookList() {
    return bookService.fetchBookList();
  }

  @PatchMapping("/books/{id}")
  public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
    return bookService.updateBook(book, id);
  }

  @DeleteMapping("/books/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBookById(@PathVariable Long id) {
    bookService.deleteBookById(id);
  }
}
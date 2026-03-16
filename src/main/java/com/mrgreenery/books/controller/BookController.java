package com.mrgreenery.books.controller;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController//Marks this class as a RESTful controller
@RequiredArgsConstructor//automated dependency injection
public class BookController{
private final BookService bookService;

  @PostMapping("/books")
  public Book saveBook(@RequestBody Book book){
    return bookService.saveBook(book);
  }

  @GetMapping("/books")
  public List<Book> fetchBookList(){
    return bookService.fetchBookList();
  }

  @PatchMapping("/books/{id}") //patch not put, so partial updates are possible. Then no need to recreate the entire object
  public Book updateBook(@RequestBody Book book, @PathVariable("id") Long id){
    return bookService.updateBook(book, id);
  }

  @DeleteMapping("/books/{id}")
  public String deleteBookById(@PathVariable("id") Long id){
    bookService.deleteBookById(id);
    return "Deleted Succesfully";
  }
}

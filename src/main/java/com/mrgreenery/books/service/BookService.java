package com.mrgreenery.books.service;

import com.mrgreenery.books.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService
{
  Book saveBook(Book book);
  List<Book> fetchBookList();
  Book updateBook(Book book, Long id);
  void deleteBookById(Long id);
}

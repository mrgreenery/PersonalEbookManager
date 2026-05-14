package com.mrgreenery.books.service;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.exception.BookNotFoundException;
import com.mrgreenery.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public List<Book> fetchBookList() {
    return bookRepository.findAll();
  }

  @Override
  public Book updateBook(Book book, Long id) {
    Book bookDb = bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));

    if (Objects.nonNull(book.getTitle()) && !book.getTitle().isBlank()) {
      bookDb.setTitle(book.getTitle());
    }
    if (Objects.nonNull(book.getAuthors()) && !book.getAuthors().isBlank()) {
      bookDb.setAuthors(book.getAuthors());
    }
    if (Objects.nonNull(book.getIsbn()) && !book.getIsbn().isBlank()) {
      bookDb.setIsbn(book.getIsbn());
    }
    if (Objects.nonNull(book.getSeries()) && !book.getSeries().isBlank()) {
      bookDb.setSeries(book.getSeries());
    }
    if (Objects.nonNull(book.getComments()) && !book.getComments().isBlank()) {
      bookDb.setComments(book.getComments());
    }
    if (Objects.nonNull(book.getRating())) {
      bookDb.setRating(book.getRating());
    }
    if (Objects.nonNull(book.getCoverUrl()) && !book.getCoverUrl().isBlank()) {
      bookDb.setCoverUrl(book.getCoverUrl());
    }
    if (Objects.nonNull(book.getPublishedDate()) && !book.getPublishedDate().isBlank()) {
      bookDb.setPublishedDate(book.getPublishedDate());
    }
    if (Objects.nonNull(book.getGenre()) && !book.getGenre().isBlank()) {
      bookDb.setGenre(book.getGenre());
    }
    if (Objects.nonNull(book.getDescription()) && !book.getDescription().isBlank()) {
      bookDb.setDescription(book.getDescription());
    }
    // boolean: altijd updaten als het request 'read' bevat
    bookDb.setRead(book.isRead());

    return bookRepository.save(bookDb);
  }

  @Override
  public void deleteBookById(Long id) {
    if (!bookRepository.existsById(id)) {
      throw new BookNotFoundException(id);
    }
    bookRepository.deleteById(id);
  }
}
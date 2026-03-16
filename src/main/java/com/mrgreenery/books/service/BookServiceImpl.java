package com.mrgreenery.books.service;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service // Marks this class as a Spring service component.
public class BookServiceImpl implements BookService
{
  private final BookRepository bookRepository;

  @Autowired
  // Constructor Injection, prioritizing simplicity and readability,
  // is widely favored for clean coding practices, compile-time safety, and ease of testing
  // Very common and preferred in Spring.Could also be done with @RequiredArgsConstructor
  public BookServiceImpl(BookRepository bookRepository)
  {
    this.bookRepository  = bookRepository;
  }


  @Override public Book saveBook(Book book)
  {
    //saves and returns the book entity
    return bookRepository.save(book);
  }

  @Override public List<Book> fetchBookList()
  {
    // Retrieves and returns a list of all book entities.
    return (List<Book>) bookRepository.findAll();
  }

  @Override public Book updateBook(Book book, Long id)
  {
    Book bookDb = bookRepository.findById(id).orElseThrow();

    // Updates fields if they are not null or empty.
    if (Objects.nonNull(book.getTitle()) && !"".equalsIgnoreCase(book.getTitle())) {
      bookDb.setTitle(book.getTitle());
    }
    if (Objects.nonNull(book.getAuthors()) && !"".equalsIgnoreCase(book.getAuthors())) {
      bookDb.setAuthors(book.getAuthors());
    }
    if (Objects.nonNull(book.getIsbn()) && !"".equalsIgnoreCase(book.getIsbn())) {
      bookDb.setIsbn(book.getIsbn());
    }
    if (Objects.nonNull(book.getSeries()) && !"".equalsIgnoreCase(book.getSeries())) {
      bookDb.setSeries(book.getSeries());
    }
    if (Objects.nonNull(book.getComments()) && !"".equalsIgnoreCase(book.getComments())) {
      bookDb.setComments(book.getComments());
    }
    if (Objects.nonNull(book.getRating())) {
      bookDb.setRating(book.getRating());
    }

    // Saves and returns the updated book entity.
    return bookRepository.save(bookDb);
  }

  @Override public void deleteBookById(Long id)
  {
    bookRepository.deleteById(id);
  }
}

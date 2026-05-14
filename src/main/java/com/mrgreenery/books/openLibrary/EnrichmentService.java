package com.mrgreenery.books.openLibrary;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.exception.BookNotFoundException;
import com.mrgreenery.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrichmentService {

  private final OpenLibraryMapper openLibraryMapper;
  private final BookRepository bookRepository;
  private final OpenLibraryService openLibraryService;

  public Book enrichBook(String isbn) {
    Book existingBook = bookRepository.findByIsbn(isbn)
        .orElseThrow(() -> new BookNotFoundException(isbn));
    OpenLibraryBook olBook = openLibraryService.fetchIsbn(isbn);
    if (olBook == null) {
      return existingBook;
    }
    Book updatedBook = openLibraryMapper.mapTo(olBook, existingBook);
    return bookRepository.save(updatedBook);
  }

  public void enrichAllBooks() throws InterruptedException {
    List<Book> books = bookRepository.findAllWithValidIsbn();
    for (Book book : books) {
      OpenLibraryBook olBook = openLibraryService.fetchIsbn(book.getIsbn());
      if (olBook == null) {
        continue;
      }
      Book updatedBook = openLibraryMapper.mapTo(olBook, book);
      bookRepository.save(updatedBook);
      Thread.sleep(350);
    }
  }
}
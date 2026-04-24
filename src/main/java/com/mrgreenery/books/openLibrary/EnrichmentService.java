package com.mrgreenery.books.openLibrary;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrichmentService
{
  private final OpenLibraryMapper openLibraryMapper;
  private final BookRepository bookRepository;
  private final OpenLibraryService openLibraryService;

  public Book enrichBook(String isbn)
  {
    Book existingBook = bookRepository.findByIsbn(isbn).orElseThrow();
    OpenLibraryBook olBook = openLibraryService.fetchIsbn(isbn);
    if (olBook == null) {
      return existingBook;
    }
    Book updatedBook = openLibraryMapper.mapTo(olBook, existingBook);
    bookRepository.save(updatedBook);
    return updatedBook;
  }

  public void enrichAllBooks() throws InterruptedException
  {
    List<Book> books = bookRepository.findAllByIsbnNot("");
    for (Book book : books)
    {
      OpenLibraryBook olBook = openLibraryService.fetchIsbn(book.getIsbn());
      if (olBook == null) {
        continue;
      }
      Book updatedBook = openLibraryMapper.mapTo(olBook, book);
      bookRepository.save(updatedBook);
      Thread.sleep(350);
      //onderzoek pagination? time outs

    }
  }
}
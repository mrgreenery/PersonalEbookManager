package com.mrgreenery.books.openLibrary;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
      return existingBook; // niets te updaten
    }
    else {
    Book updatedBook = openLibraryMapper.mapTo(olBook, existingBook);
    bookRepository.save(updatedBook);
    return updatedBook;
  }}
}

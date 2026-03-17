package com.mrgreenery.books.openLibrary;

import com.mrgreenery.books.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class OpenLibraryMapper
{
  public static Book mapTo(OpenLibraryBook olBook, Book existingBook)
  {
    return Book.builder()
        .id(existingBook.getId())
        .authors(existingBook.getAuthors())
        .title(existingBook.getTitle())
        .isbn(existingBook.getIsbn())
        .series(existingBook.getSeries())
        .comments(existingBook.getComments())
        .rating(existingBook.getRating())
        .read(existingBook.isRead())
        .coverUrl(olBook.getCover() != null ? olBook.getCover().getMedium() : null)
        .publishedDate(olBook.getPublishedDate())
        .genre(olBook.getSubjects() != null ? olBook.getSubjects().get(0).getName() : null)
        .build();
  }
}

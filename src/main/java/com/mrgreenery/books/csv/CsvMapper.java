package com.mrgreenery.books.csv;

import com.mrgreenery.books.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class CsvMapper
{
  public Book mapTo(CsvBookRepresentation c)
  {
    return Book.builder()
        .authors(c.getAuthors())
        .title(c.getTitle())
        .isbn(c.getIsbn())
        .series(c.getSeries())
        .comments(c.getComments())
        .build();
  }
}

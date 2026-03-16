package com.mrgreenery.books.csv;

import lombok.*;
import com.opencsv.bean.CsvBindByName;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvBookRepresentation
{
  @CsvBindByName(column = "title")
  private String title;

  @CsvBindByName(column = "authors")
  private String authors;

  @CsvBindByName(column = "isbn")
  private String isbn;

  @CsvBindByName(column = "series")
  private String series;
}

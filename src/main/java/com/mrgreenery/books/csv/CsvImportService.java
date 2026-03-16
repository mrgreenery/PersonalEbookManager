package com.mrgreenery.books.csv;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.repository.BookRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service

public class CsvImportService
{
  private BookRepository bookRepository;
  private CsvMapper csvMapper;

  @org.springframework.beans.factory.annotation.Value("${csv.import.path}")
  private String filename;

  public CsvImportService(BookRepository bookRepository, CsvMapper csvMapper)
  {
    this.bookRepository  = bookRepository;
    this.csvMapper = csvMapper;
  }

  public void importBooks() throws FileNotFoundException
  {
    Reader reader = new BufferedReader(new FileReader(filename));

    CsvToBean<CsvBookRepresentation> csvReader = new CsvToBeanBuilder(reader)
        .withType(CsvBookRepresentation.class)
        .withSeparator(',')
        .withIgnoreLeadingWhiteSpace(true)
        .withIgnoreEmptyLine(true)
        .build();

    List<CsvBookRepresentation> results = csvReader.parse();
    List<Book> books = results.stream()
        .map(r -> csvMapper.mapTo(r))
        .collect(Collectors.toList());

    bookRepository.saveAll(books);
  }
}


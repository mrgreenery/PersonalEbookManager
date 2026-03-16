package com.mrgreenery.books.csv;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.repository.BookRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvImportService
{
  private BookRepository bookRepository;
  private CsvMapper csvMapper;

  @org.springframework.beans.factory.annotation.Value("${csv.import.path}")
  private String filename;

  public CsvImportService(BookRepository bookRepository, CsvMapper csvMapper)
  {
    this.bookRepository = bookRepository;
    this.csvMapper = csvMapper;
  }

  public void importBooks() throws IOException
  {
    //open file from path
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));

    //delete bom (Byte Order Mark). Special bytes at the start of textfile that imply encoding and endianness)
    bis.mark(3);
    byte[] bom = new byte[3];
    bis.read(bom);
    if (!(bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF)) {
      bis.reset();
    }

    Reader reader = new InputStreamReader(bis, StandardCharsets.UTF_8);

    //parse CSV to Java objects
    CsvToBean<CsvBookRepresentation> csvReader = new CsvToBeanBuilder<CsvBookRepresentation>(reader)
        .withType(CsvBookRepresentation.class)
        .withSeparator(',')
        .withIgnoreLeadingWhiteSpace(true)
        .withIgnoreEmptyLine(true)
        .build();

    //put objects in list
    List<CsvBookRepresentation> results = csvReader.parse();

    //put into entities and save
    List<Book> books = results.stream()
        .map(r -> csvMapper.mapTo(r))
        .collect(Collectors.toList());

    bookRepository.saveAll(books);
  }
}
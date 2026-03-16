package com.mrgreenery.books.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class CsvImportController
{
  private final CsvImportService csvImportService;

  @PostMapping("/csv")
  public void importBooks()
  {
    try
    {
      csvImportService.importBooks();
    }
    catch (FileNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }
}

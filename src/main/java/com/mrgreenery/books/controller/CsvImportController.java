package com.mrgreenery.books.controller;

import com.mrgreenery.books.csv.CsvImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CsvImportController {

  private final CsvImportService csvImportService;

  @PostMapping("/csv")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void importBooks() {
    try {
      csvImportService.importBooks();
    } catch (IOException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "CSV import failed: " + e.getMessage());
    }
  }
}
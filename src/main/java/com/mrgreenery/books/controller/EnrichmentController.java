package com.mrgreenery.books.controller;

import com.mrgreenery.books.entity.Book;
import com.mrgreenery.books.openLibrary.EnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class EnrichmentController {

  private final EnrichmentService enrichmentService;

  @GetMapping("/enrich/{isbn}")
  public Book enrich(@PathVariable String isbn) {
    return enrichmentService.enrichBook(isbn);
  }

  @PostMapping("/enrich/all")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void enrichAll() {
    try {
      enrichmentService.enrichAllBooks();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Enrichment interrupted");
    }
  }
}
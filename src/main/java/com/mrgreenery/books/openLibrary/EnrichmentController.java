package com.mrgreenery.books.openLibrary;

import com.mrgreenery.books.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnrichmentController {

 private final EnrichmentService enrichmentService;

  @GetMapping("/enrich/{isbn}")
  public Book enrich(@PathVariable String isbn) {
    return enrichmentService.enrichBook(isbn);
  }

  @PostMapping("/enrich/all")
  public void enrichAll() throws InterruptedException {
    enrichmentService.enrichAllBooks();
  }
}
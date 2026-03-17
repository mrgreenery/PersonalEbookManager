package com.mrgreenery.books.openLibrary;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OpenLibraryService
{
  RestClient restClient = RestClient.builder()
      .baseUrl("https://openlibrary.org/api/books")
      .build();

  public OpenLibraryBook fetchIsbn(String isbn)
  {
    ResponseEntity<Map<String, OpenLibraryBook>> response = restClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("bibkeys", "ISBN:" + isbn)
            .queryParam("format", "json")
            .queryParam("jscmd", "data")
            .build())                          // close uriBuilder
        .retrieve()
        .toEntity(new ParameterizedTypeReference<Map<String, OpenLibraryBook>>() {});

    Map<String, OpenLibraryBook> body = response.getBody();
    if (body == null || body.isEmpty()) {
      return null;
    }
    return body.values().iterator().next();

  }

}

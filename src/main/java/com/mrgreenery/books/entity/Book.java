package com.mrgreenery.books.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @NotBlank(message = "Title is required")
  private String title;

  @NotBlank(message = "Authors is required")
  private String authors;

  private String isbn;
  private String series;
  private Double rating;
  private boolean read = false;
  private String coverUrl;
  private String publishedDate;
  private String genre;

  @Column(columnDefinition = "TEXT")
  private String comments;

  @Column(columnDefinition = "TEXT")
  private String description;
}
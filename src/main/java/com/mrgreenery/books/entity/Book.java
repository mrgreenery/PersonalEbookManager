package com.mrgreenery.books.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book
{
  @GeneratedValue(strategy = GenerationType.IDENTITY) //this is here because PostgreSQL SERIAL/uses auto-increment
  @Id private Long id;
  private String authors;
  private String isbn;
  private String title;
  private String series;
  private Double rating; //Double, not double, so it can have null value.
  private boolean read = false;
  private String coverUrl;
  private String publishedDate;
  private String genre;

  @Column(columnDefinition = "TEXT") //comments were varchar(255) but some are longer. SQL type TEXT has no limit
  private String comments;

  @Column(columnDefinition = "TEXT")
  private String description;
}

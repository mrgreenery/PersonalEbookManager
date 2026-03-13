package com.wilber.books;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book
{
  @GeneratedValue(strategy = GenerationType.IDENTITY) //this is here because PostgreSQL SERIAL/uses auto-increment
  @Id private Long id;
  private String author;
  private String isbn;
  private String title;
  private String series;
  private String comments;
  private double rating;
  private boolean read = false;

  public Book()
  {
  }


}

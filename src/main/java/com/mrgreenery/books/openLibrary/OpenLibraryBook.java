package com.mrgreenery.books.openLibrary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenLibraryBook
{
  @Getter
  @Setter
  @NoArgsConstructor
  static class Cover {
    private String medium;
  }

  @Getter
  @Setter
  @NoArgsConstructor
    static class Author{
    private String name;
  }

  @Getter
  @Setter
  @NoArgsConstructor
   static class Subject{
    private String name;
  }


  @JsonProperty("publish_date") //to match response
  private String publishedDate;
  private String title;
  private List<Author> authors;
  private Cover cover;
  private List<Subject> subjects;

}

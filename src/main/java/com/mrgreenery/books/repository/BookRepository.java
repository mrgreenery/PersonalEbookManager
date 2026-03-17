  package com.mrgreenery.books.repository;

  import com.mrgreenery.books.entity.Book;
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.stereotype.Repository;
  import java.util.Optional;

  @Repository// Indicates that this interface is a Spring Data repository.
  public interface BookRepository extends JpaRepository<Book, Long>
  {
    Optional<Book> findByIsbn(String isbn);
  }

package com.mrgreenery.books.repository;

import com.mrgreenery.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Optional<Book> findByIsbn(String isbn);

  @Query("SELECT b FROM Book b WHERE b.isbn IS NOT NULL AND b.isbn <> ''")
  List<Book> findAllWithValidIsbn();
}
package org.example.librarymanagement.repo;

import org.example.librarymanagement.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public  interface BookRepository extends CrudRepository<Book, Long> {
     List<Book> findAllByBname(String bname);

}
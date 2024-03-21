package org.example.librarymanagement.repo;

import org.example.librarymanagement.entity.Book;
import org.example.librarymanagement.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByEmail(String email);

}
package org.example.librarymanagement.controllers;

import org.example.librarymanagement.dto.BookDto;
import org.example.librarymanagement.dto.BorrowRequest;
import org.example.librarymanagement.dto.FineDto;
import org.example.librarymanagement.dto.UserDto;
import org.example.librarymanagement.manager.LibraryManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class Api {
    @Autowired
    LibraryManagerImpl libraryManagerImpl;
    @GetMapping("/api/book")
    ResponseEntity<BookDto> getBook(@RequestParam("name") Optional<String> name) {
        if (name.orElse("").isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(libraryManagerImpl.getBook(name.get()));
    }

    @PostMapping("/api/book")
    ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        if (bookDto==null) {
            return ResponseEntity.ok().build();
        }
        libraryManagerImpl.addBook(bookDto);
        return null;
    }

    @PostMapping("/api/user")
    ResponseEntity<UserDto> addUser(@RequestBody UserDto UserDto) {
        if (UserDto==null) {
            return ResponseEntity.ok().build();
        }
        libraryManagerImpl.addUser(UserDto);
        return null;
    }

    @PostMapping("/api/book/order")
    ResponseEntity borrowBook(@RequestBody BorrowRequest borrowRequest) {
        if (borrowRequest==null) {
            return ResponseEntity.ok().build();
        }
        try {
            libraryManagerImpl.borrowBook(borrowRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @DeleteMapping("/api/book/order")
    ResponseEntity returnBook(@RequestBody BorrowRequest borrowRequest) {
        if (borrowRequest==null) {
            return ResponseEntity.ok().build();
        }
        libraryManagerImpl.returnBook(borrowRequest);
        return null;
    }
    @GetMapping("/api/user/{email}/fine_amount")
    ResponseEntity<FineDto> getFine(@PathVariable("email") String email) {
        if (email==null || email.isEmpty()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.ok().body(libraryManagerImpl.getFine(email));
    }

    @PutMapping("/api/user/{user_id}/fine_amount")
        ResponseEntity<FineDto> payFine(@PathVariable("user_id") String userId,@RequestBody FineDto fineDto) {
        if (userId==null || userId.isEmpty() || fineDto==null) {
            return ResponseEntity.ok().build();
        }
        libraryManagerImpl.payFine(userId, fineDto.getFine());
        return null;
    }


}
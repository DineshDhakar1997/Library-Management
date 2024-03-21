package org.example.librarymanagement.manager;

import org.example.librarymanagement.dto.BookDto;
import org.example.librarymanagement.dto.BorrowRequest;
import org.example.librarymanagement.dto.FineDto;
import org.example.librarymanagement.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface LibraryManager
{

    public BookDto getBook(String name);

    public void addBook(BookDto bookDto);

    public void addUser(UserDto UserDto);

    public void borrowBook(BorrowRequest borrowRequest) throws Exception;

    public void returnBook(BorrowRequest borrowRequest);

    public FineDto getFine(String userId);

    public void payFine(String userId, Double fine);
}
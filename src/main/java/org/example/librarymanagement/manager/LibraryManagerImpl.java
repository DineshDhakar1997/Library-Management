package org.example.librarymanagement.manager;

import org.example.librarymanagement.dto.BookDto;
import org.example.librarymanagement.dto.BorrowRequest;
import org.example.librarymanagement.dto.FineDto;
import org.example.librarymanagement.dto.UserDto;
import org.example.librarymanagement.entity.*;
import org.example.librarymanagement.repo.BookRepository;
import org.example.librarymanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryManagerImpl implements LibraryManager {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public BookDto getBook(String name) {
        List<Book> books = bookRepository.findAllByBname(name);
        if (books.isEmpty()) {
            return BookDto.builder().build();
        }
        Book book = books.get(0);
        return BookDto.builder()
                .author(book.getAuthor())
                .bname(book.getBname())
                .qty(book.getInventory().getQty())
                .build();
    }

    @Override
    public void addBook(BookDto bookDto) {
        Inventory inventory = Inventory.builder()
                .qty(bookDto.getQty()).build();

        Book book = Book.builder()
                .bname(bookDto.getBname())
                .author(bookDto.getAuthor())
                .inventory(inventory)
                .build();
        inventory.setBook(book);
        bookRepository.save(book);
    }

    @Override
    public void addUser(UserDto UserDto) {
        FineAmount fineAmount = FineAmount.builder().fine(0d).build();
        User user = User.builder()
                .email(UserDto.getEmail())
                .fineAmount(fineAmount)
                .build();
        userRepository.save(user);
    }

    @Override
    public void borrowBook(BorrowRequest borrowRequest) throws Exception {
        List<Book> books = bookRepository.findAllByBname(borrowRequest.getBook());
        Optional<User> user = userRepository.findByEmail(borrowRequest.getEmail());
        if(books.isEmpty() ) {
            throw new Exception("Book not found");
        }
        if(user.isEmpty()) {
            throw new Exception("User not found");
        }
        if(books.get(0).getInventory().getQty()<=0) {
            throw new Exception("Book not available");
        }
        if(user.get().getBook()!=null) {
            throw new Exception("User already has a book");
        }
        if(user.get().getFineAmount().getFine()>0d) {
            throw new Exception("User has pending fine");
        }
        Book book= books.get(0);
        Integer qty = book.getInventory().getQty();
        book.getInventory().setQty(qty-1);
        bookRepository.save(book);

        if (book.getUsers() == null){
            book.setUsers(Arrays.asList(user.get()));
        }
        else {
            book.getUsers().add(user.get());

            user.get().setBook(books.get(0));
            BorrowHistory borrowHistory = BorrowHistory.builder()
                    .user(user.get())
                    .dateTime(Date.valueOf(LocalDate.now().toString()))
                            .build();


            user.get().setBorrowHistory(borrowHistory);
            bookRepository.save(book);
            userRepository.save(user.get());

        }

    }

    @Override
    public void returnBook(BorrowRequest borrowRequest) {

    }

    @Override
    public FineDto getFine(String userId) {
        return null;
    }

    @Override
    public void payFine(String userId, Double fine) {

    }
}
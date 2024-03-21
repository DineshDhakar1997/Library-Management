package org.example.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Builder
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bname;
    private String author;
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Inventory inventory;
    @OneToMany
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;


}
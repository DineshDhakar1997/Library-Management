package org.example.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private FineAmount fineAmount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @OneToOne(mappedBy = "user")
    private BorrowHistory borrowHistory;


}
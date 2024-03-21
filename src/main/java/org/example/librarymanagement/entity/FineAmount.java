package org.example.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Builder
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FineAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double fine;
    @OneToOne
    private User user;




}
package org.example.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @Temporal(TemporalType.DATE)
    private Date dateTime;
}
package org.example.librarymanagement.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Setter
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String bname;
    private String author;
    private Integer qty;

}
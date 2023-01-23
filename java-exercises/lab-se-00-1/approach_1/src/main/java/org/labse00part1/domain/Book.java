package org.labse00part1.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private int pages;
    private String ISBN;
    private Author author;
}

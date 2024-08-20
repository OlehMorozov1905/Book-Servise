package ait.cohort34.book.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    String isbn;
    String title;
    Set<AuthorDto> authors;
    String publisher;
}

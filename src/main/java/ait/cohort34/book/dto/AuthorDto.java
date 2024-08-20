package ait.cohort34.book.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode(of = "name")
public class AuthorDto {
    String name;
    LocalDate birthDate;
}

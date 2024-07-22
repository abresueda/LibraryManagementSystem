package dev.patika.Library.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private Long publisherId;
}

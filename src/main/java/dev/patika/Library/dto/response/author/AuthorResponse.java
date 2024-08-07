package dev.patika.Library.dto.response.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private int id;
    private String name;
    private int birthDate;
    private String country;
}

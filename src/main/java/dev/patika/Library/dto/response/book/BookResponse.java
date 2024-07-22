package dev.patika.Library.dto.response.book;

import com.fasterxml.jackson.annotation.JsonView;
import dev.patika.Library.dto.response.publisher.PublisherResponse;
import dev.patika.Library.dto.views.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private int publicationYear;

    @JsonView(Views.Public.class)
    private int stock;

    @JsonView(Views.Public.class)
    private PublisherResponse publisherResponse;
}

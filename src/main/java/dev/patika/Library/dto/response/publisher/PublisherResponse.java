package dev.patika.Library.dto.response.publisher;

import com.fasterxml.jackson.annotation.JsonView;
import dev.patika.Library.dto.views.Views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private int establishmentYear;

}

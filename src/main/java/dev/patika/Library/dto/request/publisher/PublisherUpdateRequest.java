package dev.patika.Library.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherUpdateRequest {

    @NotNull(message = "Yayınevi adı boş veya null olamaz.")
    private String name;
    private int establishmentYear;
    private String address;
}

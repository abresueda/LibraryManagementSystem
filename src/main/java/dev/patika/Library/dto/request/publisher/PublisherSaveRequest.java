package dev.patika.Library.dto.request.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherSaveRequest {
    @NotNull(message = "Yayınevi adı boş veya null olamaz.")
    private String name;
    private int establishmentYear;
    private String address;

}

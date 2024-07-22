package dev.patika.Library.dto.response.borrowing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {
    private int id;
    private String borrowerName;
    private String borrowerEmail;
    private Date borrowerDate;
    private Date returnDate;
}

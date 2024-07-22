package dev.patika.Library.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowing")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id")
    private int id;

    @NotNull
    @Column(name = "borrower_name")
    private String borrowerName;

    @Email
    @Column(name = "borrower_email")
    private String borrowerEmail;

    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

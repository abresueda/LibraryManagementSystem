package dev.patika.Library.business.abstracts;

import dev.patika.Library.entities.Borrowing;
import org.springframework.data.domain.Page;

public interface IBorrowingService {

    Borrowing save(Borrowing borrowing);

    Borrowing get(int id);

    Page<Borrowing> cursor(int page, int pageSize);

    Borrowing update(Borrowing borrowing);

    boolean delete(int id);
}

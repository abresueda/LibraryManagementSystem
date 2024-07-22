package dev.patika.Library.business.concretes;

import dev.patika.Library.business.abstracts.IBookService;
import dev.patika.Library.business.abstracts.IBorrowingService;
import dev.patika.Library.core.exception.NotFoundException;
import dev.patika.Library.core.ulties.Msg;
import dev.patika.Library.dao.BorrowingRepo;
import dev.patika.Library.entities.Book;
import dev.patika.Library.entities.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BorrowingManager implements IBorrowingService {
    private final BorrowingRepo borrowingRepo;
    private final IBookService bookService;

    public BorrowingManager(BorrowingRepo borrowingRepo, IBookService bookService) {
        this.borrowingRepo = borrowingRepo;
        this.bookService = bookService;
    }

    @Override
    public Borrowing save(Borrowing borrowing) {
        Book book = bookService.get(borrowing.getBook().getId());
        if (book.getStock() <= 0) {
            throw new IllegalStateException("Yetersiz stok. Kitap ödünç verilemedi.");
        }
        book.setStock(book.getStock() - 1);
        bookService.update(book);
        return this.borrowingRepo.save(borrowing);
    }

    @Override
    public Borrowing get(int id) {
        return this.borrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Borrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.borrowingRepo.findAll(pageable);
    }

    @Override
    public Borrowing update(Borrowing borrowing) {
        this.get(borrowing.getId());
        return this.borrowingRepo.save(borrowing);
    }

    @Override
    public boolean delete(int id) {
        Borrowing borrowing = this.get(id);
        this.borrowingRepo.delete(borrowing);
        return true;
    }
}

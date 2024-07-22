package dev.patika.Library.api;

import dev.patika.Library.business.abstracts.IBorrowingService;
import dev.patika.Library.dto.request.borrowing.BookBorrowingRequest;
import dev.patika.Library.dto.response.borrowing.BookBorrowingResponse;
import dev.patika.Library.entities.Borrowing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrowings")
public class BorrowingController {
    private final IBorrowingService borrowingService;
    private final ModelMapper modelMapper;

    @Autowired
    public BorrowingController(IBorrowingService borrowingService, ModelMapper modelMapper) {
        this.borrowingService = borrowingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<BookBorrowingResponse> save (@RequestBody BookBorrowingRequest bookBorrowingRequest) {
        Borrowing borrowing = modelMapper.map(bookBorrowingRequest, Borrowing.class);
        Borrowing savedBorrowing = borrowingService.save(borrowing);
        BookBorrowingResponse savedBorrowingResponse = modelMapper.map(savedBorrowing, BookBorrowingResponse.class);
        return new ResponseEntity<>(savedBorrowingResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookBorrowingResponse> get (@PathVariable int id) {
        Borrowing borrowing = borrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = modelMapper.map(borrowing, BookBorrowingResponse.class);
        return ResponseEntity.ok(bookBorrowingResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookBorrowingResponse> update (@PathVariable int id, @RequestBody BookBorrowingRequest bookBorrowingRequest) {
        Borrowing bookBorrowing = modelMapper.map(bookBorrowingRequest, Borrowing.class);
        bookBorrowing.setId(id);
        Borrowing updatedBookBorrowing = borrowingService.update(bookBorrowing);
        BookBorrowingResponse updatedBookBorrowingResponse = modelMapper.map(updatedBookBorrowing, BookBorrowingResponse.class);
        return ResponseEntity.ok(updatedBookBorrowingResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable int id) {
        borrowingService.delete(id);
        return ResponseEntity.ok("Ödünç alma işlemi silinmiştir.");
    }
}

package dev.patika.Library.api;

import com.fasterxml.jackson.annotation.JsonView;
import dev.patika.Library.business.abstracts.IBookService;
import dev.patika.Library.dto.request.book.BookRequest;
import dev.patika.Library.dto.response.book.BookResponse;
import dev.patika.Library.dto.views.Views;
import dev.patika.Library.entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final IBookService bookService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookController(IBookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<BookResponse> save (@RequestBody BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        Book savedBook = bookService.save(book);
        BookResponse savedBookDTO = modelMapper.map(savedBook, BookResponse.class);
        return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<BookResponse> get(@PathVariable int id) {
        Book book = bookService.get(id);
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<Page<BookResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Book> bookPage = bookService.cursor(page, size);
        List<BookResponse> bookResponse = bookPage.getContent().stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .collect(Collectors.toList());
        Page<BookResponse> bookResponsePage = new PageImpl<>(bookResponse, bookPage.getPageable(), bookPage.getTotalElements());
        return ResponseEntity.ok(bookResponsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update (@PathVariable int id, @RequestBody BookRequest bookRequest) {
        bookRequest.setId(id);
        Book book = modelMapper.map(bookRequest, Book.class);
        Book updatedBook = bookService.update(book);
        BookResponse updatedBookResponse = modelMapper.map(updatedBook, BookResponse.class);
        return ResponseEntity.ok(updatedBookResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete (@PathVariable int id) {
        bookService.delete(id);
        return ResponseEntity.ok("Kitap silindi.");
    }
}

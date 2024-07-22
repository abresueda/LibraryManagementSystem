package dev.patika.Library.api;

import dev.patika.Library.business.abstracts.IAuthorService;
import dev.patika.Library.dto.request.author.AuthorRequest;
import dev.patika.Library.dto.response.author.AuthorResponse;
import dev.patika.Library.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> save (@RequestBody AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBirthDate(authorRequest.getBirthDate());
        author.setCountry(authorRequest.getCountry());
        Author savedAuthor = authorService.save(author);
        AuthorResponse response = new AuthorResponse(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getBirthDate(), savedAuthor.getCountry());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> get (@PathVariable int id) {
        Author author = authorService.get(id);
        AuthorResponse response = new AuthorResponse(author.getId(), author.getName(), author.getBirthDate(), author.getCountry());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update (@PathVariable int id, @RequestBody AuthorRequest authorRequest) {
        Author author = authorService.get(id);
        author.setName(authorRequest.getName());
        author.setBirthDate(authorRequest.getBirthDate());
        author.setCountry(authorRequest.getCountry());
        Author updatedAuthor = authorService.update(author);
        AuthorResponse response = new AuthorResponse(updatedAuthor.getId(), updatedAuthor.getName(), updatedAuthor.getBirthDate(), updatedAuthor.getCountry());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete (@PathVariable int id) {
        boolean result = authorService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Author>> cursor (@RequestParam int page, @RequestParam int pageSize) {
        Page<Author> authors = authorService.cursor(page, pageSize);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}

package dev.patika.Library.business.abstracts;

import dev.patika.Library.entities.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {

    Author save(Author author);

    Author get(int id);

    Page<Author> cursor(int page, int pageSize);

    Author update(Author author);

    boolean delete(int id);
}

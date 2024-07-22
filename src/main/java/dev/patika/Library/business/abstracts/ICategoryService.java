package dev.patika.Library.business.abstracts;

import dev.patika.Library.entities.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {

    Category save(Category category);

    Category get(int id);

    Page<Category> cursor(int page, int pageSize);

    Category update(Category category);

    String delete(int id);
}

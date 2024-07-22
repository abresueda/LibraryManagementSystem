package dev.patika.Library.business.concretes;

import dev.patika.Library.business.abstracts.ICategoryService;
import dev.patika.Library.core.exception.NotFoundException;
import dev.patika.Library.core.ulties.Msg;
import dev.patika.Library.dao.BookRepo;
import dev.patika.Library.dao.CategoryRepo;
import dev.patika.Library.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {
    private final CategoryRepo categoryRepo;
    private final BookRepo bookRepo;

    public CategoryManager(CategoryRepo categoryRepo, BookRepo bookRepo) {
        this.categoryRepo = categoryRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category category = this.get(id);
        if (!this.bookRepo.findByCategoryId(id).isEmpty()) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        }
        this.categoryRepo.delete(category);
        return "Kategori başarıyla silindi.";
    }
}

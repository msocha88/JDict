package pl.micsoc.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.micsoc.dictionary.model.Category;
import pl.micsoc.dictionary.repository.CategoryRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void save(Category category) {

        Set<Category> categories = new HashSet<>(categoryRepository.findAll());

        if (!categories.contains(categoryRepository.findByName(category.getName()))) {
            categoryRepository.save(category);
        }
    }

    public Set<Category> allCategories() {
        return new HashSet<>(categoryRepository.findAll());
    }

    public Category findByName(Category category) {
        return categoryRepository.findByName(category.getName());
    }

    public Category findFromThymeleaf(String selectedCategory) {

        categoryRepository.findByName(selectedCategory);

        return null;
    }
}

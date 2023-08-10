package com.dockerspring.springdocker.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dockerspring.springdocker.model.Category;
import com.dockerspring.springdocker.repositories.CategoryRepository;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(long id) {
        Category categoryId = categoryRepository.findById(id);

        if(categoryId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category) {
        Category categoryName = categoryRepository.findByName(category.getName());

        if(categoryName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        category.setCreationDate(LocalDate.now());
        category.setLastUpdate(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    public Category updateCategory(Category updatedCategory, long id) {

        Category categoryDatabase = categoryRepository.findById(id);

        if(categoryDatabase == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Category category = categoryDatabase;

        category.setName(updatedCategory.getName());
        category.setCreationDate(categoryDatabase.getCreationDate());
        category.setLastUpdate(LocalDateTime.now());
        category.setEmployee(category.getEmployee());

        return categoryRepository.save(category);
    }

    public void delete(Long id) {

        Optional<Category> categoryId = categoryRepository.findById(id);


        if(!categoryId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        categoryRepository.deleteById(id);
    }
}
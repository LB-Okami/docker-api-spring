package com.dockerspring.springdocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerspring.springdocker.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);

    Category findById(long id);
}

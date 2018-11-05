package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Category;

import java.util.List;

public interface CategoryDao {

    Integer create(Category category);

    boolean update(Category category);

    boolean delete(Category category);

    Category findById(Long id);

    List<Category> findAll();

    Category findByName(String name);

}

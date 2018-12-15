package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Category;

import java.util.List;

public interface CategoryDao {

    Long create(Category category);

    Category update(Long id, String name);

    boolean delete(Category category);

    Category findById(Long id);

    Category findByName(String name);

    List<Category> findAll();

}

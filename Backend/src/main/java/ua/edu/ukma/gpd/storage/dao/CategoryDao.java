package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Category;

import java.util.List;

public interface CategoryDao {

    Long create(Category category);

    boolean update(Category category);

    boolean delete(Category category);

    Category findById(Long id);

    Category findByName(String name);

    List<Category> findAll();

}

package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryByName(String name) throws Exception;

    Category getCategoryById(Long id) throws Exception;

    Integer add(Category category) throws Exception;

    List<Category> findAll() throws Exception;
}

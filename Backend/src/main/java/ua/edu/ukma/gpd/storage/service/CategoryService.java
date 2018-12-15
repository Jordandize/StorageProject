package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Category;

import java.util.List;

public interface CategoryService {

    Long add(Category category) throws Exception;
    
    boolean update(Category category) throws Exception;
    
    boolean delete(Category category) throws Exception;

    Category update(Long id, String name) throws Exception;

    boolean delete(Category category) throws Exception;

    Category getById(Long id) throws Exception;

    Category getByName(String name) throws Exception;

    List<Category> getAll() throws Exception;
    
}

package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.CategoryDao;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.exception.NotUniqueValueException;
import ua.edu.ukma.gpd.storage.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Long add(Category category) throws Exception {
        try {
            Category exists = getByName(category.getName());
            if (exists == null) {
                return categoryDao.create(category);
            } else {
            	throw new NotUniqueValueException("Category", "name", category.getName());
            }
        } catch (Exception e) {
            throw new Exception("Exeption occured in CategoryServiceImpl: operation add [" + category + "] failed.");
        }
    }

    @Override
    public Category getByName(String name) throws Exception {
        try {
            return categoryDao.findByName(name);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in CategoryServiceImpl: operation getCategoryByName [" + name + "] failed.", e);
        }
    }

    @Override
    public Category getById(Long id) throws Exception {
        try {
            return categoryDao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in CategoryServiceImpl: operation getCategoryById [" + id + "] failed.", e);
        }
    }

    @Override
    public List<Category> getAll() throws Exception{
        try {
        	return categoryDao.findAll();
        } catch (Exception e){
            throw new Exception("CategoryServiceImpl: Get all categories operation failed", e);
        }
    }
    
}

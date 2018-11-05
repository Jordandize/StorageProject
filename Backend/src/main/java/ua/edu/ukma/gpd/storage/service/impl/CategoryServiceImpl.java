package ua.edu.ukma.gpd.storage.service.impl;

import com.sun.deploy.security.ruleset.ExceptionRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.CategoryDao;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Integer add(Category category) throws Exception {
        try {
            boolean exists = categoryDao.findByName(category.getName()) != null;
            if (!exists) {
                Integer id = categoryDao.create(category);
                return id;
            } else {
                throw new Exception("Exeption occured in CategoryServiceImpl: operation add [" + category.getName() + "] failed.");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public Category getCategoryByName(String name) throws Exception {
        Category category;
        try{
            category = categoryDao.findByName(name);
        } catch (EmptyResultDataAccessException e){
            category = null;
        } catch (Exception e){
            throw new Exception("Exeption occured in CategoryServiceImpl: operation getCategoryByName [" + name + "] failed.", e);
        }
        return category;
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category;
        try{
            category = categoryDao.findById(id);
        } catch (EmptyResultDataAccessException e){
             category = null;

        } catch (Exception e){
            throw new Exception("Exeption occured in CategoryServiceImpl: operation getCategoryById [" + id + "] failed.", e);
        }
        return category;
    }

    @Override
    public List<Category> findAll() throws Exception{
        try{
            return categoryDao.findAll();
        } catch (Exception e){
            throw new Exception("CategoryServiceImpl: Get all categories operation failed", e);
        }
    }
}

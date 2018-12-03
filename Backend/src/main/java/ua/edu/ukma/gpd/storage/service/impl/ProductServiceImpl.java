package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.exception.NotUniqueValueException;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductDao productDao;

    @Override
    public Long add(Product product) throws Exception {
        try {
            Product exists = getByName(product.getName());
            if (exists == null) {
                return productDao.create(product);
            } else {
              throw new NotUniqueValueException("Product", "name", product.getName());
            }
        } catch (Exception e) {
        	throw new Exception("Exeption occured in ProductServiceImpl: operation add [" + product + "] failed.", e);
        }
    }
    
    @Override
    public boolean update(Product product) throws Exception {
    	 try {
             return productDao.update(product);
         } catch (EmptyResultDataAccessException e){
             return false;
        } catch (Exception e) {
        	throw new Exception("Exeption occured in ProductServiceImpl: operation update [" + product + "] failed.", e);
        }
    }
    @Override
    public boolean delete(Product product) throws Exception {
    	 try {
             return productDao.delete(product);
         } catch (EmptyResultDataAccessException e){
             return false;
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Exeption occured in ProductServiceImpl: operation delete [" + product + "] failed.", e);
        }
    }

    @Override
    public Product getById(Long id) throws Exception {
        try {
            return productDao.findById(id);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getById [" + id + "] failed.", e);
        }
    }

	@Override
	public Product getByName(String name) throws Exception {
        try {
            return productDao.findByName(name);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getByName [" + name + "] failed.", e);
        }
	}

    @Override
    public List<Product> getAll() throws Exception{
        try {
            return productDao.findAll();
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
    }
    
    @Override
    public List<Product> getByCategory(Category category) throws Exception {
        try {
            return productDao.findByCategory(category);
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
    }
}

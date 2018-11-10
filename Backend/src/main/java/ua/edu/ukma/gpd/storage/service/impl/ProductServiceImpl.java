package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
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
}

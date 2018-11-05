package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductDao productDao;

    @Override
    public Long add(Product product) throws Exception {
        try{
            boolean exists = productDao.findByName(product.getName()) != null;
            if (!exists){
                Long id = productDao.create(product);
                return id;
            } else {
              throw new Exception("Exeption occured in ProductServiceImpl: operation add [" + product.getName() + "] failed.");
            }
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public Product findById(Long id) throws Exception {
        Product product;
        try {
            product = productDao.findById(id);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getProductById [" + id + "] failed.", e);
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws Exception{
        try {
            return productDao.findAll();
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        } catch (Exception e){
            throw new Exception("ProductServiceImpl: Get all products operation failed", e);
        }
    }
}

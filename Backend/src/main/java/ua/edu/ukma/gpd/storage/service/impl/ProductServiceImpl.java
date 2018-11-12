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
    public Long add(Product product) throws Exception{
        Long id = null;
        try{
            Product exists = getById(product.getProdId());
            if (exists == null){
                System.out.println("creating");
                id = productDao.create(product);
            }
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            System.out.println("here!");
            throw new Exception("Exeption occured in ProductServiceImpl: operation add [" + product.getName() + "] failed.");
        }
        return id;
    }

    @Override
    public Product getById(Long id) throws Exception {
        try {
            return productDao.findById(id);
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            throw new Exception("Exeption occured in ProductServiceImpl: operation getProductById [" + id + "] failed.");
        }
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

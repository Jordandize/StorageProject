package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;

import java.util.List;

public interface ProductService {

    Long add(Product product) throws Exception;
    
    boolean update(Product product) throws Exception;
    
    boolean delete(Product product) throws Exception;

    Product getById(Long id) throws Exception;

    Product getByName(String name) throws Exception;

    List<Product> getAll() throws Exception;
    
    List<Product> getByCategory(Category category) throws Exception;
    
}

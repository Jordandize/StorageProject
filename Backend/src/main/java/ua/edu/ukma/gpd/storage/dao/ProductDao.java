package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;

import java.util.List;

public interface ProductDao {

    Long create(Product product);

    boolean update(Product product);

    boolean delete(Product product);

    Product findById(Long id);

    Product findByName(String name);

    List<Product> findAll();
    
    List<Product> findByCategory(Category category);

}

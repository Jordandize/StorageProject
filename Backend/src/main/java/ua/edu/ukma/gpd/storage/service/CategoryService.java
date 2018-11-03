package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Category;

public interface CategoryService {

    Category getCategoryByName(String name) throws Exception;


}

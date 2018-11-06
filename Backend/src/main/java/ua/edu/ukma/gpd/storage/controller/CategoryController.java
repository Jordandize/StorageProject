package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.CategoryDto;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        List<Category> categories;
        try{
            categories = categoryService.findAll();
        } catch (Exception e){
            e.printStackTrace();
            categories = null;
        }
        return categories;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id){
        Category category;
        try{
            category = categoryService.getCategoryById(id);
        } catch (Exception e){
            e.printStackTrace();
            category = null;
        }
        return category;
    }

    @PostMapping
    public ResponseEntity<Integer> addCategory(@Valid @RequestBody CategoryDto form) throws Exception{
        HttpStatus status;
        Integer id;
        try {
            Category category = buildUserFromDto(form);
            id = categoryService.add(category);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id =- 1;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(id, status);
    }

    private Category buildUserFromDto(CategoryDto form){
        Category category = new Category();
        category.setName(form.getName());
        return category;
    }

}

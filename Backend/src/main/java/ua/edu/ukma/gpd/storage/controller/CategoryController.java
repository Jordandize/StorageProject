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

import javax.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() throws Exception {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) throws Exception{
        return categoryService.getById(id);
    }
  
    @PostMapping
    public ResponseEntity<Long> addCategory(@Valid @RequestBody CategoryDto form) throws Exception{
        HttpStatus status;
        Long id;
        try {
            Category category = buildCategoryFromDto(form);
            id = categoryService.add(category);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id = (long) -1;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(id, status);
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Boolean> updateProduct(@Valid @RequestBody CategoryDto form,@PathVariable("id") Long id) throws Exception{
        HttpStatus status;
        boolean b;
        try{
            Category category = buildCategoryFromDto(form);
            category.setId(id);
            b = categoryService.update(category);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            b=false;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(b, status);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) throws Exception{
        HttpStatus status;
        boolean b;
        try{
        	Category category = categoryService.getById(id);
            b = categoryService.delete(category);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            b=false;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(b, status);
    }
  
   

    private Category buildCategoryFromDto(CategoryDto form){
        Category category = new Category();
        category.setName(form.getName());
        return category;
    }
    @PostMapping("/update={id}/set={name}")
    public Category updateCategory(@PathVariable("id") Long id, @PathVariable("name") String name) throws Exception{
        return categoryService.update(id, name);
    }

//    @PostMapping("delete={id}")
//    public boolean deleteCategory(@PathVariable("id") Long id) throws Exception{
//        return categoryService.delete(id);
//    }

}

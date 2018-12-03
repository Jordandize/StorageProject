package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.dto.ProductDto;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.CategoryService;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> getProducts(
    		@RequestParam(value = "category", required = false) Long categoryId) throws Exception {
    	System.out.println(categoryId);
    	if(categoryId == null) {
    		return productService.getAll();
    	} else {
        	Category category = categoryService.getById(categoryId);
        	if(category != null) {
        		return productService.getByCategory(category);
        	} else {
        		return new ArrayList<>();
        	}
    	}
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        Product product;
        try{
            product = productService.getById(id);
        } catch (Exception e){
            e.printStackTrace();
            product = null;
        }
        return product;

    }

    @PostMapping
    public ResponseEntity<Long> addProduct(@Valid @RequestBody ProductDto form) throws Exception{
        HttpStatus status;
        Long id;
        try{
        	  System.out.println(form.toString());
            Product product = buildProductFromDto(form);
          
            id = productService.add(product);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id = (long) -1;
            status = HttpStatus.BAD_REQUEST;
        }
        System.out.println(id);
        return new ResponseEntity<>(id, status);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Boolean> updateProduct(@Valid @RequestBody ProductDto form,@PathVariable("id") Long id) throws Exception{
        HttpStatus status;
        boolean b;
        try{
            Product product = buildProductFromDto(form);
            product.setId(id);
            b = productService.update(product);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            b=false;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(b, status);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@Valid @RequestBody ProductDto form,@PathVariable("id") Long id) throws Exception{
        HttpStatus status;
        boolean b;
        try{
            Product product = buildProductFromDto(form);
            product.setId(id);
            b = productService.delete(product);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            b=false;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(b, status);
    }
    
    private Product buildProductFromDto(ProductDto form){
        Product product = new Product();
        product.setName(form.getName());
        product.setAmount(form.getAmount());
        product.setCategoryId(form.getCategoryId());
        product.setDescription(form.getDescription());
        product.setActive(form.getActive());
        product.setImage(form.getImage());
        product.setIcon(form.getIcon());
        return product;
    }
    
}

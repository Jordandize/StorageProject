package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.CategoryService;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
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
    public Product getProductById(@PathVariable("id") Long id) throws Exception{
        return productService.getById(id);
    }

    /*@PostMapping
    public ResponseEntity<Long> addProduct(@Valid @RequestBody ProductDto form) throws Exception{
        HttpStatus status;
        Long id;
        try{
            Product product = buildProductFromDto(form);
            id = productService.add(product);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id = (long) -1;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(id, status);
    }

    private Product buildProductFromDto(ProductDto form){
        Product product = new Product();
        product.setName(form.getName());
        product.setAmount(form.getAmount());
        product.setAnnotation(form.getAnnotation());
        product.setActive(form.getActive());
        return product;
    }*/
}

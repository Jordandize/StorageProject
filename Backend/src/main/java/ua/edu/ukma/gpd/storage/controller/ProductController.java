package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() throws Exception{
        return productService.getAll();
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

package ua.edu.ukma.gpd.storage.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.OrderProductDto;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.OrderProductService;
import ua.edu.ukma.gpd.storage.service.ProductService;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/orders_products")
public class OrderProductController {

    @Autowired
    private OrderProductService opService;
    
    @Autowired
    private ProductService prService;

    @GetMapping
    public List<OrderProduct> getOrdersProducts(){
        List<OrderProduct> orderProductList;
        try {
            orderProductList = opService.findAll();
        } catch (Exception e){
            e.printStackTrace();
            orderProductList = null;
        }
        return orderProductList;
    }
    @GetMapping("/products/{orderId}")
  public  List< Product>  getProductByOrder(@PathVariable(value = "orderId") Long orderId)throws Exception{
    List< OrderProduct>  orderProduct;
    List< Product>  products=new ArrayList<Product>();
    orderProduct = opService.findByOrder(orderId);
    for(int i=0;i<orderProduct.size();i++) {
       products.add(prService.getById(orderProduct.get(i).getProductId()));
      products.get(i).setAmount(orderProduct.get(i).getAmount());
    }
      return  products;
 }

//    @GetMapping("/{orderId, productId}")
//    public OrderProduct getProductById(Long orderId, Long productId){
//        OrderProduct orderProduct;
//        try{
//            orderProduct = opService.findById(orderId, productId);
//        } catch (Exception e){
//            e.printStackTrace();
//            orderProduct = null;
//        }
//        return orderProduct;
//    }

//    @PostMapping
//    public ResponseEntity<OrderProduct> addOrderProduct(@Valid @RequestBody OrderProductDto form) throws Exception{
//        HttpStatus status;
//        OrderProduct orderProduct;
//        try{
//            //orderProduct = buildOrderProductFromDto(form);
//            status = HttpStatus.OK;
//        } catch (Exception e){
//            e.printStackTrace();
//            orderProduct = null;
//            status = HttpStatus.BAD_REQUEST;
//        }
//        return new ResponseEntity<>(orderProduct, status);
//    }
//


//    private OrderProduct buildOrderProductFromDto(OrderProductDto form, ){
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.
//        orderProduct.setProductId(form.getProductId());
//        orderProduct.setAmount();
//        orderProduct.setAmountReturned(form.getAmountReturned());
//        return orderProduct;
//    }


}

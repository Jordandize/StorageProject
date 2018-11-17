package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.OrderDto;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;
import ua.edu.ukma.gpd.storage.service.OrderProductService;
import ua.edu.ukma.gpd.storage.service.OrderService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService opService;

    @GetMapping
    public List<Order> getAll(){
        List<Order> orders = null;
        try{
            orders = orderService.findAll();
        } catch (Exception  e){
            e.printStackTrace();
            orders = null;
        }
        return orders;
    }

    @GetMapping("/{user}")
    public List<Order> getOrdersForUser(@PathVariable(value = "user") Long userId) throws Exception{
//        System.out.println("Get!");
//        if (userId != null) {
            return orderService.findOrdersForUser(userId);
//        } else {
//            return orderService.findAll();
//        }
    }

    @PostMapping
    public ResponseEntity<Long> addOrder(@Valid @RequestBody OrderDto form) throws Exception{
        HttpStatus status;
        Long id;
        try{
            Order order = buildOrder(form);
            id = orderService.add(order);
            List<OrderProduct> products = buildProducts(form, id);
            for(OrderProduct op : products){
                opService.add(op);
            }

            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id =(long) -1;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(id, status);
    }

    private List<OrderProduct> buildProducts(OrderDto form, Long id){
        List<OrderProduct> products = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : form.getProducts().entrySet()){
            OrderProduct op = new OrderProduct();
            op.setProductId(entry.getKey());
            op.setOrderId(id);
            op.setAmount(entry.getValue());
            op.setAmountReturned(0);
        }
        return products;
    }

    // FOR EDITING ANOTHER CONTROLLER

    private Order buildOrder(OrderDto form){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Order order = new Order();
        order.setParentId((long)2);
        order.setOrderStatus(1);
        order.setOrderType(form.getOrderType());
        order.setCreationDateTime(timestamp);
        order.setModifiedDateTime(timestamp);
        order.setAnnotation(form.getAnnotation());
        order.setArchived(false);
        order.setCreatedBy(form.getCreatedBy());
        order.setAssignedTo((long)1);
        System.out.println(order);
        return order;
    }
}

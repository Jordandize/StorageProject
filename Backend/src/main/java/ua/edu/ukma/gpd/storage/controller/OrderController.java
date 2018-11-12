package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.OrderDto;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.service.OrderService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

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

    @GetMapping("/{id_user}")
    public List<Order> findOrdersForUser(@RequestParam(required = true) Long userId){
        List<Order> orders = null;
        try {
            orders = orderService.findOrdersForUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            orders = null;
        }
        return orders;
    }

    @PostMapping
    public ResponseEntity<Long> addOrder(@Valid @RequestBody OrderDto form) throws Exception{
        HttpStatus status;
        Long id;
        try{
            Order order = buildOrder(form);
            id = orderService.add(order);
            status = HttpStatus.OK;
        } catch (Exception e){
            e.printStackTrace();
            id =(long) -1;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(id, status);
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

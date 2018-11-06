package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.OrderDto;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.service.OrderService;

import javax.print.attribute.standard.OrientationRequested;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order();
        order.setParentId(null);
        order.setOrderStatus(1);
        order.setOrderType(form.getOrderType());
        order.setCreationDateTime(dtf.format(now));
        order.setModifiedDateTime(null);
        order.setArchived(false);
        order.setCreatedBy(form.getCreatedBy());
        order.setAssignedTo(null);
        return order;
    }
}

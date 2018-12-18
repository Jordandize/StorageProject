package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.gpd.storage.dto.OrderDto;
import ua.edu.ukma.gpd.storage.dto.ShortageDto;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;
import ua.edu.ukma.gpd.storage.service.OrderProductService;
import ua.edu.ukma.gpd.storage.service.OrderService;

import javax.validation.Valid;
import java.sql.Timestamp;
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
    public List<Order> getAll(@RequestParam(value = "status", required = false)
    	OrderStatus status) throws Exception {
    	return status != null 
    			? orderService.getForKeeperByStatus(status)
    			: orderService.findAll();
    }
    
    @PostMapping("/{id}/ready")
    public ResponseEntity<Order> setOrderReady(@PathVariable Long id) throws Exception {
    	Order order = orderService.setReady(id);
    	return order != null
    			? new ResponseEntity<>(order, HttpStatus.OK) 
    			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/{id}/closed")
    public ResponseEntity<Order> setOrderClosed(@PathVariable Long id) throws Exception {
    	Order order = orderService.setClosed(id);
    	return order != null
    			? new ResponseEntity<>(order, HttpStatus.OK) 
    			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/{id}/shortage")
    public ResponseEntity<List<ShortageDto>> getOrderShortage(@PathVariable Long id)
    		throws Exception {
    	List<ShortageDto> shortage = orderService.getShortageForOrder(id);
    	return shortage != null
    			? new ResponseEntity<>(shortage, HttpStatus.OK)
    			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/id_keeper=null")
    public List<Order> getUnasgnedOrders() {
        List<Order> orders = null;
        try {
            orders = orderService.findUnassignedOrders();
        } catch (Exception e) {
            e.printStackTrace();
            orders = null;
        }
        return orders;
    }

    @GetMapping("/{userId}")
    public List<Order> getOrdersForUser(@PathVariable(value = "userId") Long userId) throws Exception {
        return orderService.findOrdersForUser(userId);
    }
    
    @GetMapping("/oneOrder/{orderId}")
    public Order getOrderById(@PathVariable(value = "orderId") Long orderId) throws Exception{
    	return orderService.findById(orderId);
    }

    @PostMapping("/{id_order}/assignKeeper/{id_user}")
    public ResponseEntity<Order> assignKeeperToOrder(@PathVariable Long id_order, @PathVariable Long id_user) {
        Order order;
        HttpStatus status;
        try {
            order = orderService.assignKeeperToOrder(id_user, id_order);
            status = HttpStatus.OK;
        } catch (Exception e) {
            order = null;
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(order, status);
    }

    @PostMapping
    public ResponseEntity<Long> addOrder(@Valid @RequestBody OrderDto form) throws Exception {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Long id;
        try {
            Order order = buildOrder(form);
            id = orderService.add(order);
            List<OrderProduct> products = buildProducts(form, id);
            if (products.isEmpty())
                orderService.delete(order);
            for (OrderProduct op : products) {
                opService.add(op);
            }
            status = HttpStatus.OK;

        } catch (Exception e) {
            e.printStackTrace();
            id = (long) -1;
        }
        return new ResponseEntity<>(id, status);
    }

    private List<OrderProduct> buildProducts(OrderDto form, Long id) {
        List<OrderProduct> products = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : form.getProducts().entrySet()) {
            OrderProduct op = new OrderProduct();
            op.setProductId(entry.getKey());
            op.setOrderId(id);
            op.setAmount(entry.getValue());
            op.setAmountReturned(0);
            System.out.println(op);
            products.add(op);
        }
        return products;
    }

    private Order buildOrder(OrderDto form) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Order order = new Order();
        order.setParentId((long) 2);
        order.setOrderStatus(OrderStatus.OPENED.name());
        order.setOrderType(form.getOrderType());
        order.setCreationDateTime(timestamp);
        order.setModifiedDateTime(timestamp);
        order.setAnnotation(form.getAnnotation());
        order.setArchived(false);
        order.setCreatedBy(form.getCreatedBy());
        System.out.println(order);
        return order;
    }

    @PostMapping("/{id}/decline")
    public ResponseEntity<Order> declineOrder(@PathVariable Long id) throws Exception {
    	Order order = orderService.declineOrder(id);
    	return order != null
    			? new ResponseEntity<>(order, HttpStatus.OK) 
    			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) throws Exception {
    	Order order = orderService.cancelOrder(id);
    	return order != null
    			? new ResponseEntity<>(order, HttpStatus.OK) 
    			: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
      
}

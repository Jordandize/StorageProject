package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;

import java.util.List;

public interface OrderService {

    Long add(Order order) throws Exception;

    void delete(Order order) throws Exception;

    Order findById(Long id) throws Exception;

    List<Order> findAll() throws Exception;
    
    List<Order> getForKeeperByStatus(OrderStatus status) throws Exception;

    List<Order> findOrdersForUser(Long userId) throws Exception;

    List<Order> findUnassignedOrders() throws Exception;

    Order assignKeeperToOrder(Long userId, Long orderId) throws Exception;
    
    Order setReady(Long id) throws Exception;
    
    Order setClosed(Long id) throws Exception;

    Order declineOrder(Long orderId) throws Exception;

}

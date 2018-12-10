package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Order;

import java.util.List;

public interface OrderService {

    Long add(Order order) throws Exception;

    Order findById(Long id) throws Exception;

    List<Order> findAll() throws Exception;
    
    List<Order> getForKeeper(String statusAsString, String statusAsNumber) throws Exception;

    List<Order> findOrdersForUser(Long userId) throws Exception;

    List<Order> findUnassignedOrders() throws Exception;

    Order assignKeeperToOrder(Long userId, Long orderId) throws Exception;
    
    Order setReady(Long id) throws Exception;
    
    Order setClosed(Long id) throws Exception;

}

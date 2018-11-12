package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Order;

import java.util.List;

public interface OrderService {

    Long add(Order order);

    Order findById(Long id) throws Exception;

    List<Order> findAll() throws Exception;

    List<Order> findOrdersForUser(Long userId) throws Exception;

}

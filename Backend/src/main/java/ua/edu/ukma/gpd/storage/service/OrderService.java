package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Order;

import java.util.List;

public interface OrderService {

    Long add(Order order);

    List<Order> findOrdersForUser(Long userId) throws Exception;

}

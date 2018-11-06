package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Order;

import java.util.List;

public interface OrderDao {

    Long create(Order order);

    Order findById(Long id);

    List<Order> findAll();

    List<Order> findOrdersForUser(Long userId);

}

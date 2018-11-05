package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    Integer create(OrderStatus orderStatus);

    boolean update(OrderStatus orderStatus);

    boolean delete(OrderStatus orderStatus);

    OrderStatus findById(Integer id);
    
    OrderStatus findByName(String name);

    List<OrderStatus> findAll();
}

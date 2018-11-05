package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    Integer create(OrderStatus orderStatus);

    boolean update(OrderStatus orderStatus);

    boolean delete(OrderStatus orderStatus);

    List<OrderStatus> findAll();

    OrderStatus findById(Integer id);
}

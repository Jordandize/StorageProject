package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;
import ua.edu.ukma.gpd.storage.entity.OrderType;

import java.util.List;

public interface OrderDao {

    Long create(Order order, OrderType orderType, OrderStatus orderStatus);

    Order findById(Long id);

    List<Order> findAll();


}

package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderType;

import java.util.List;

public interface OrderTypeDao {

    Integer create(OrderType orderType);

    boolean update(OrderType orderType);
    
    boolean updateOrderType(Order order, OrderType orderType);

    boolean delete(OrderType orderType);

    OrderType findById(Integer id);
    
    OrderType findByName(String name);

    List<OrderType> findAll();

}

package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.OrderType;

import java.util.List;

public interface OrderTypeDao {

    Integer create(OrderType orderType);

    boolean update(OrderType orderType);

    boolean delete(OrderType orderType);

    List<OrderType> findAll();

    OrderType findById(Integer id);

}

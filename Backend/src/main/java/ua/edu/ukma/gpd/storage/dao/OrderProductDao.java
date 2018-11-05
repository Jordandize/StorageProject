package ua.edu.ukma.gpd.storage.dao;


import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.entity.Product;

import java.util.List;

public interface OrderProductDao {

    Long create(OrderProduct orderProduct, Order order, Product product);

    boolean update(OrderProduct orderProduct);

    boolean delete(OrderProduct orderProduct);

    List<OrderProduct> findAll();

    OrderProduct findProductAmount(Integer amount);

}

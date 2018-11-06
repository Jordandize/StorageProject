package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.entity.Product;

import java.util.List;

public interface OrderProductService {

    Long add(OrderProduct orderProduct, Order order, Product product) throws Exception;

    OrderProduct findById(Long orderId, Long productId) throws Exception;

    List<OrderProduct> findAll() throws Exception;
}

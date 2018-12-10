package ua.edu.ukma.gpd.storage.service;

import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import java.util.List;

public interface OrderProductService {

    OrderProduct add(OrderProduct orderProduct) throws Exception;

    OrderProduct findById(Long orderId, Long productId) throws Exception;
    
    List< OrderProduct>  findByOrder(Long orderId) throws Exception;

    List<OrderProduct> findAll() throws Exception;
}

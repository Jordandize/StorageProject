package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.dto.ShortageDto;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;

import java.util.List;

public interface OrderProductDao {

    OrderProduct create(OrderProduct orderProduct);

    boolean update(OrderProduct orderProduct);

    boolean delete(OrderProduct orderProduct);

    List<OrderProduct> findAll();

    OrderProduct findProductAmount(Integer amount);

    OrderProduct findById(Long orderId, Long productId);
    
    List<ShortageDto> findShortageForOrder(Long id);
    
    boolean reserveProductsForOrder(Long id);
    
    boolean writeOffProductsForOrder(Long id);
    
    List< OrderProduct> findByOrder(Long orderId);

}

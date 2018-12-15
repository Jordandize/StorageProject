package ua.edu.ukma.gpd.storage.dao;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;

import java.util.List;

public interface OrderDao {

    Long create(Order order);
    
    Order update(Order order);

    Order findById(Long id);

    List<Order> findAll();
    
    List<Order> findForKeeperByStatus(Long id, OrderStatus status);

    List<Order> findOrdersForUser(Long userId);

    Order assignKeeperToOrder(Long userId, Long orderId);

    List<Order> findUnassignedOrders();

    Order declineOrder(Long orderId);

}

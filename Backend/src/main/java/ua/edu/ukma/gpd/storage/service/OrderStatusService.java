package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;

public interface OrderStatusService {
	
	Integer add(OrderStatus orderStatus) throws Exception;
	
	OrderStatus getById(Integer id) throws Exception;

	OrderStatus getByName(String name) throws Exception;

	List<OrderStatus> getAll() throws Exception;
	
	boolean setStatus(Order order, OrderStatus orderStatus) throws Exception;

}

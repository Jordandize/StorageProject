package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderType;

public interface OrderTypeService {

	Integer add(OrderType orderType) throws Exception;
	
	OrderType getById(Integer id) throws Exception;

	OrderType getByName(String name) throws Exception;

	List<OrderType> getAll() throws Exception;
	
	boolean setType(Order order, OrderType orderType) throws Exception;

}

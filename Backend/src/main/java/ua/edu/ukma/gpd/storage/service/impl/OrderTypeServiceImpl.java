package ua.edu.ukma.gpd.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.dao.OrderTypeDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderType;
import ua.edu.ukma.gpd.storage.service.OrderTypeService;

@Service
public class OrderTypeServiceImpl implements OrderTypeService {

	@Autowired
	private OrderTypeDao orderTypeDao;
	
	@Override
	public Integer add(OrderType orderType) throws Exception {
		Integer id;
		try {
			id = orderTypeDao.create(orderType);
		} catch (Exception e) {
			e.printStackTrace();
			id = null;
		}
		return id;
	}

	@Override
	public OrderType getById(Integer id) throws Exception {
		OrderType orderType;
		try {
			orderType = orderTypeDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			orderType = null;
		}
		return orderType;
	}

	@Override
	public OrderType getByName(String name) throws Exception {
		OrderType orderType;
		try {
			orderType = orderTypeDao.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			orderType = null;
		}
		return orderType;
	}

	@Override
	public List<OrderType> getAll() throws Exception {
		List<OrderType> orderTypes;
		try {
			orderTypes = orderTypeDao.findAll();
		} catch (Exception e) {
			orderTypes = null;
		}
		return orderTypes;
	}

	@Override
	public boolean setType(Order order, OrderType orderType) throws Exception {
		boolean set;
		try {
			set = orderTypeDao.updateOrderType(order, orderType);
		} catch (Exception e) {
			e.printStackTrace();
			set = false;
		}
		return set;
	}

}

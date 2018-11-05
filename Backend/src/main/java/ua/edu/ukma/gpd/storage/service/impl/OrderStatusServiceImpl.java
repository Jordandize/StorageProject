package ua.edu.ukma.gpd.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.dao.OrderStatusDao;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;
import ua.edu.ukma.gpd.storage.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	private OrderStatusDao orderStatusDao;
	
	@Override
	public Integer add(OrderStatus orderStatus) throws Exception {
		Integer id;
		try {
			id = orderStatusDao.create(orderStatus);
		} catch (Exception e) {
			e.printStackTrace();
			id = null;
		}
		return id;
	}

	@Override
	public OrderStatus getById(Integer id) throws Exception {
		OrderStatus orderStatus;
		try {
			orderStatus = orderStatusDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			orderStatus = null;
		}
		return orderStatus;
	}

	@Override
	public OrderStatus getByName(String name) throws Exception {
		OrderStatus orderStatus;
		try {
			orderStatus = orderStatusDao.findByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			orderStatus = null;
		}
		return orderStatus;
	}

	@Override
	public List<OrderStatus> getAll() throws Exception {
		List<OrderStatus> orderStatuses;
		try {
			orderStatuses = orderStatusDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			orderStatuses = null;
		}
		return orderStatuses;
	}

}

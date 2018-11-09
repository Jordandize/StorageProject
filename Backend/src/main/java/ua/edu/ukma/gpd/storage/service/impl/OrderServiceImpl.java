package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.OrderDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Long add(Order order) {
        Long id = null;
        try{
           boolean exists = orderDao.findById(order.getId()) != null;
           if(!exists){
               id = orderDao.create(order);

           }
        } catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    public List<Order> findOrdersForUser(Long userId) throws Exception{
        List<Order> orders = null;
        try{
            orders = orderDao.findOrdersForUser(userId);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            orders = null;
        } catch (Exception e){
            throw new Exception("Exeption occured in OrderServiceImpl: operation findOrdersForUser [" + userId + "] failed.", e);
        }
        return orders;
    }
}

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
           Order exists = findById(order.getId());
           if(exists == null){
               System.out.println("not found");
               id = orderDao.create(order);
           }
        } catch (Exception e){
            System.out.println("exactly");
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Order findById(Long id) throws Exception{
        try{
            Order order = orderDao.findById(id);
            return order;
        } catch (EmptyResultDataAccessException e){
            return null;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Exception occured in OrderServiceImpl: operation findById with id = " + id+ "failed");
        }
    }

    @Override
    public List<Order> findAll() throws Exception{
        List<Order> orders = null;
        try{
            orders = orderDao.findAll();
        } catch (EmptyResultDataAccessException e){

        } catch (Exception e){
            throw new Exception("Exception occured in OrderServiceImpl: operation findAll failed");
        }
        return orders;
    }

    public List<Order> findOrdersForUser(Long userId) throws Exception{
        List<Order> orders = null;
        try{
            orders = orderDao.findOrdersForUser(userId);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            orders = null;
        } catch (Exception e){
            throw new Exception("Exception occured in OrderServiceImpl: operation findOrdersForUser [" + userId + "] failed.", e);
        }
        return orders;
    }
}

package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.OrderDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;
import ua.edu.ukma.gpd.storage.service.OrderService;
import ua.edu.ukma.gpd.storage.service.UserService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private UserService userService;

    @Override
    public Long add(Order order) {
        Long id = null;
        try{
           Order exists = findById(order.getId());
           if(exists == null){
               id = orderDao.create(order);
           }
        } catch (Exception e){
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delete(Order order){
        try{
            Order exists = orderDao.findById(order.getId());
            if (exists != null){
                orderDao.delete(order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
    
    @Override
    public List<Order> getForKeeperByStatus(OrderStatus status) throws Exception {
    	try {
        	UserDetails userDetails =
          			 (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	Long keeperId = userService.getByEmail(userDetails.getUsername()).getId();
        	return orderDao.findForKeeperByStatus(keeperId, status);
    	} catch (Exception ex) {
    		throw new Exception("OrderServiceImp.getForKeeper() failed", ex);
		}
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

    public List<Order> findUnassignedOrders() throws Exception{
        List<Order> orders = null;
        try{
            orders = orderDao.findUnassignedOrders();
        } catch (EmptyResultDataAccessException e){

        } catch (Exception e){
            throw new Exception("Exeption occured in OrderServiceImpl: operation findUnassignedOrders failed.", e);
        }
        return orders;
    }

    public Order assignKeeperToOrder(Long userId, Long orderId) throws Exception{
        Order order = null;
        try{
            order = orderDao.assignKeeperToOrder(userId, orderId);
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        } catch (Exception e){
            throw new Exception("Exception occured in OrderServiceImpl: operation assignKeeperToOrder [" +  userId + ", " + orderId + "] failed", e);
        }
        return order;
    }

    public Order declineOrder(Long orderId) throws Exception{
    	Order order = findById(orderId);
    	if(order != null) {
        	//order.setOrderStatus(OrderStatus.DECLINED.name());
        	//order.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
        	order = orderDao.declineOrder(orderId);
    	}
    	return order;
    }
    
    public Order setReady(Long id) throws Exception {
    	Order order = findById(id);
    	if(order != null) {
        	order.setOrderStatus(OrderStatus.READY.name());
        	order.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
        	order = orderDao.update(order);
    	}
    	return order;
    }
    
    public Order setClosed(Long id) throws Exception {
    	Order order = findById(id);
    	if(order != null) {
        	order.setOrderStatus(OrderStatus.CLOSED.name());
        	order.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
        	order = orderDao.update(order);
    	}
    	return order;
    }
    
    public Order cancelOrder(Long orderId) throws Exception{
    	Order order = findById(orderId);
    	if(order != null) {
        	//order.setOrderStatus(OrderStatus.CANCELED.name());
        	//order.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
        	order = orderDao.cancelOrder(orderId);
    	}
    	return order;
    }
}

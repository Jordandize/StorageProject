package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.OrderProductDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.service.OrderProductService;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductDao orderProductDao;

    @Override
    public Long add(OrderProduct orderProduct, Order order, Product product) throws Exception {
        try {
            boolean exists = orderProductDao.findById(order.getOrderId(), product.getProdId()) != null;
            if(!exists){
                Long id = orderProductDao.create(orderProduct, order, product);
                return id;
            } else {
                throw new Exception("Exeption occured in OrderProductServiceImpl: operation add [" + orderProductDao.findById(order.getOrderId(),product.getProdId()) + "] failed.");
            }
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Override
    public OrderProduct findById(Long orderId, Long productId) throws Exception {
        OrderProduct orderProduct;
        try{
            orderProduct = orderProductDao.findById(orderId, productId);
            return orderProduct;
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        } catch (Exception e){
            throw new Exception("Exeption occured in OrderProductServiceImpl: operation findById [" + orderId +" and "+ productId + "] failed.", e);
        }
        return null;
    }

    @Override
    public List<OrderProduct> findAll() throws Exception {
        return null;
    }
}

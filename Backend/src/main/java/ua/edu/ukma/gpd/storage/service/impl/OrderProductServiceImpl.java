package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.edu.ukma.gpd.storage.dao.OrderProductDao;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.service.OrderProductService;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductDao orderProductDao;

    @Override
    public OrderProduct add(OrderProduct orderProduct) throws Exception {
        OrderProduct createdOrderProduct = null;
        try {
                createdOrderProduct = orderProductDao.create(orderProduct);
        } catch (Exception e){
            throw new Exception(e);
        }
        return createdOrderProduct;
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
    public  List< OrderProduct>  findByOrder(Long orderId) throws Exception {
    	 List< OrderProduct>  orderProduct=null;
        try{
            orderProduct = orderProductDao.findByOrder(orderId);
            return orderProduct;
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            orderProduct=null;
        } catch (Exception e){
        	e.printStackTrace();
            throw new Exception("Exeption occured in OrderProductServiceImpl: operation findByOrder [" + orderId +" ] failed.", e);
        }
        return  orderProduct;
    }

    @Override
    public List<OrderProduct> findAll() throws Exception {
        return null;
    }
}

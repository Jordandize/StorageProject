package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderProductDao;
import ua.edu.ukma.gpd.storage.dto.ShortageDto;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.sql.OrderProductSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderProductDaoImpl implements OrderProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderProductDaoImpl(DataSource dataSource){ jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<OrderProduct> mapper = (resultSet, i) -> {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(resultSet.getLong("id_product"));
        orderProduct.setOrderId(resultSet.getLong("id_order"));
        orderProduct.setAmount(resultSet.getInt("amount"));
        orderProduct.setAmountReturned(resultSet.getInt("amount_returned"));
        return orderProduct;
    };

    // FIX this method
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
         jdbcTemplate.update(connection -> {
             PreparedStatement ps = connection.prepareStatement(OrderProductSql.INSERT);
             ps.setLong(1, orderProduct.getOrderId());
             ps.setLong(2, orderProduct.getProductId());
             ps.setInt(3, orderProduct.getAmount());
             ps.setInt(4, orderProduct.getAmountReturned());
             return ps;
         });
         return orderProduct;
    }

    @Override
    public boolean update(OrderProduct orderProduct) {
        return false;
    }

    @Override
    public boolean delete(OrderProduct orderProduct) {
        return false;
    }

    @Override
    public OrderProduct findById(Long orderId, Long productId){
        return jdbcTemplate.queryForObject(OrderProductSql.FIND_BY_ID, new Object[] {orderId, productId}, mapper);
    }
    
    @Override
    public List<ShortageDto> findShortageForOrder(Long id) {
    	return jdbcTemplate.query(OrderProductSql.FIND_SHORTAGE, new Object[] { id }, (rs, i) -> {
    		ShortageDto shortage = new ShortageDto();
    		shortage.setProductId  (rs.getLong  ("id"));
    		shortage.setProductName(rs.getString("name"));
    		shortage.setShortage   (rs.getInt   ("shortage"));
			return shortage;
		});
    }
    

    @Override
    public boolean reserveProductsForOrder(Long id) {
    	return jdbcTemplate.update(OrderProductSql.RESERVE_PRODUCTS_FOR_ORDER, id) > 0;
    }
    
    @Override
    public boolean writeOffProductsForOrder(Long id) {
    	return jdbcTemplate.update(OrderProductSql.WRITE_OFF_RESERVED_PRODUCTS_FOR_ORDER, id) > 0;
    }
    
    @Override
    public  List< OrderProduct>  findByOrder(Long orderId){
        return jdbcTemplate.query(OrderProductSql.FIND_BY_ORDER, new Object[] {orderId}, mapper);
    }

    @Override
    public List<OrderProduct> findAll() {
        return jdbcTemplate.query(OrderProductSql.FIND_ALL, mapper);
    }

    @Override
    public OrderProduct findProductAmount(Integer amount) {
        return null;
    }
}

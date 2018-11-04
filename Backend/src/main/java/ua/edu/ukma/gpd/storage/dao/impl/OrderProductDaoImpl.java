package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ua.edu.ukma.gpd.storage.dao.OrderProductDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderProduct;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.sql.OrderProductSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

public class OrderProductDaoImpl implements OrderProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderProductDaoImpl(DataSource dataSource){ jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<OrderProduct> mapper = (resultSet, i) -> {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(resultSet.getLong("productId"));
        orderProduct.setOrderId(resultSet.getLong("orderId"));
        orderProduct.setAmount(resultSet.getInt("amount"));
        orderProduct.setAmountReturned(resultSet.getInt("amountReturned"));
        return orderProduct;
    };

    @Override
    public Long create(OrderProduct orderProduct, Order order, Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(OrderProductSql.INSERT,
                // TODO
               // product.getProdId(), order.,
                orderProduct.getAmount(), orderProduct.getAmountReturned());
        orderProduct.setProductId((Long) keyHolder.getKey());
        return null;
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
    public List<OrderProduct> findAll() {
        return null;
    }

    @Override
    public OrderProduct findProductAmount(Integer amount) {
        return null;
    }
}

package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.sql.OrderSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Order> mapper = (resultSet, i) -> {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setParentId(resultSet.getLong("id_parent"));
        order.setOrderStatus(resultSet.getInt("id_order_status"));
        order.setOrderType(resultSet.getInt("id_order_type"));
        order.setCreationDateTime(resultSet.getTimestamp("created"));
        order.setModifiedDateTime(resultSet.getTimestamp("changed"));
        order.setAnnotation(resultSet.getString("annotation"));
        order.setArchived(resultSet.getBoolean("archived"));
        order.setCreatedBy(resultSet.getLong("id_user"));
        order.setAssignedTo(resultSet.getLong("id_keeper"));
        return order;
    };

    @Override
    public Long create(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            System.out.println("here");
            PreparedStatement ps = connection.prepareStatement(OrderSql.INSERT, new String[] {"id"});
            ps.setLong(1, order.getParentId());
            ps.setInt(2, order.getOrderType());
            ps.setInt(3, order.getOrderStatus());
            ps.setTimestamp(4, order.getCreationDateTime());
            ps.setTimestamp(5, order.getModifiedDateTime());
            ps.setString(6, order.getAnnotation());
            ps.setBoolean(7, order.getArchived());
            ps.setLong(8, order.getCreatedBy());
            ps.setLong(9, order.getAssignedTo());
            System.out.println(ps);
            return ps;
        }, keyHolder);
        order.setId((Long) keyHolder.getKey());
        return order.getId();
    }

    @Override
    public Order findById(Long id) {
        return jdbcTemplate.queryForObject(OrderSql.FIND_BY_ID,
                new Object[] { id }, mapper);
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(OrderSql.FIND_ALL, mapper);
    }

    @Override
    public List<Order> findOrdersForUser(Long userId) {
        return jdbcTemplate.query(OrderSql.FIND_ORDERS_FOR_USER, mapper);
    }


}

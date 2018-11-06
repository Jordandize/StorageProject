package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderDao;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;
import ua.edu.ukma.gpd.storage.entity.OrderType;
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
        order.setOrderType(resultSet.getInt("id_order_type"));
        order.setOrderStatus(resultSet.getInt("id_order_status"));
        order.setCreationDateTime(resultSet.getString("created"));
        order.setModifiedDateTime(resultSet.getString("changed"));
        order.setAnnotation(resultSet.getString("annotation"));
        order.setArchived(resultSet.getBoolean("isArchived"));
        order.setCreatedBy(resultSet.getLong("createdBy"));
        order.setAssignedTo(resultSet.getLong("assignedTo"));
        return order;
    };

    @Override
    public Long create(Order order, OrderType orderType, OrderStatus orderStatus) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderSql.INSERT, new String[] {"id"});
            ps.setLong(2, order.getParentId());
            ps.setInt(3, orderType.getId());
            ps.setInt(4, orderStatus.getId());
            ps.setString(5, order.getCreationDateTime());
            ps.setString(6, order.getModifiedDateTime());
            ps.setString(7, order.getAnnotation());
            ps.setBoolean(8, order.getArchived());
            ps.setLong(9, order.getCreatedBy());
            ps.setLong(10, order.getAssignedTo());
            return ps;
        }, keyHolder);
        order.setId((Long) keyHolder.getKey());
        return order.getId();
    }

    @Override
    public Order findById(Long id) {
        return jdbcTemplate.queryForObject(OrderSql.FIND_BY_ID, new String[] {"id"}, mapper);
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(OrderSql.FIND_ALL, mapper);
    }
}

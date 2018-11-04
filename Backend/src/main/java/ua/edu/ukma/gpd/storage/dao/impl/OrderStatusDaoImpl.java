package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderStatusDao;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;
import ua.edu.ukma.gpd.storage.sql.OrderTypeSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderStatusDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<OrderStatus> mapper = (resultSet, i) -> {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatusId(resultSet.getInt("id"));
        orderStatus.setStatusName(resultSet.getString("name"));
        return orderStatus;
    };

    @Override
    public Integer create(OrderStatus orderStatus) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderTypeSql.INSERT, new String[] {"id"});
            ps.setString(2, orderStatus.getStatusName());
            return ps;
        }, keyHolder);
        orderStatus.setStatusId((Integer) keyHolder.getKey());
        return orderStatus.getStatusId();
    }

    @Override
    public boolean update(OrderStatus orderStatus) {
        return false;
    }

    @Override
    public boolean delete(OrderStatus orderStatus) {
        return false;
    }

    @Override
    public List<OrderStatus> findAll() {
        return null;
    }

    @Override
    public OrderStatus findById(Integer id) {
        return null;
    }
}

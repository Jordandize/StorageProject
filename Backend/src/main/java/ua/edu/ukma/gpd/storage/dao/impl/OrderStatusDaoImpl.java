package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderStatusDao;
import ua.edu.ukma.gpd.storage.entity.OrderStatus;
import ua.edu.ukma.gpd.storage.sql.OrderStatusSql;
import ua.edu.ukma.gpd.storage.sql.OrderTypeSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderStatusDaoImpl implements OrderStatusDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderStatusDaoImpl(DataSource dataSource) 
    	{ jdbcTemplate = new JdbcTemplate(dataSource); }

    private RowMapper<OrderStatus> mapper = (rs, i) -> {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(rs.getInt("id"));
        orderStatus.setName(rs.getString("name"));
        return orderStatus;
    };

    @Override
    public Integer create(OrderStatus orderStatus) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderTypeSql.INSERT,
            		new String[] { "id" });
            ps.setString(1, orderStatus.getName());
            return ps;
        }, keyHolder);
        orderStatus.setId((Integer) keyHolder.getKey());
        return orderStatus.getId();
    }

    @Override
    public boolean update(OrderStatus orderStatus) {
        return jdbcTemplate.update(OrderStatusSql.UPDATE, orderStatus.getName(), 
        		orderStatus.getId()) > 0;
    }

    @Override
    public boolean delete(OrderStatus orderStatus) {
        return jdbcTemplate.update(OrderStatusSql.DELETE, orderStatus.getId()) > 0;
    }

    @Override
    public OrderStatus findById(Integer id) {
        return jdbcTemplate.queryForObject(OrderStatusSql.FIND_BY_ID,
        		new Object[] { id }, mapper);
    }

	@Override
	public OrderStatus findByName(String name) {
		return jdbcTemplate.queryForObject(OrderStatusSql.FIND_BY_NAME,
				new Object[] { name }, mapper);
	}
	
    @Override
    public List<OrderStatus> findAll() {
    	return jdbcTemplate.query(OrderStatusSql.FIND_ALL, mapper);
    }

}

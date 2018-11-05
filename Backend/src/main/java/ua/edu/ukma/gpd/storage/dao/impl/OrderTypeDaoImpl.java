package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.OrderTypeDao;
import ua.edu.ukma.gpd.storage.entity.OrderType;
import ua.edu.ukma.gpd.storage.sql.OrderTypeSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class OrderTypeDaoImpl implements OrderTypeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderTypeDaoImpl(DataSource dataSource) 
    	{ jdbcTemplate = new JdbcTemplate(dataSource); }

    private RowMapper<OrderType> mapper = (rs, i) -> {
        OrderType orderType = new OrderType();
        orderType.setId(rs.getInt("id"));
        orderType.setName(rs.getString("name"));
        return orderType;
    };

    @Override
    public Integer create(OrderType orderType) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderTypeSql.INSERT, 
            		new String[] { "id" } );
            ps.setString(1, orderType.getName());
            return ps;
        }, keyHolder);
        orderType.setId((Integer) keyHolder.getKey());
        return orderType.getId();
    }

    @Override
    public boolean update(OrderType orderType) {
        return jdbcTemplate.update(OrderTypeSql.UPDATE, 
        		mapper, orderType.getName(), orderType.getId()) > 0;
    }

    @Override
    public boolean delete(OrderType orderType) {
        return jdbcTemplate.update(OrderTypeSql.DELETE, orderType.getId()) > 0;
    }

	@Override
	public OrderType findById(Integer id) {
		return jdbcTemplate.queryForObject(OrderTypeSql.FIND_BY_ID, 
				new Object[] { id }, mapper);
	}

	@Override
	public OrderType findByName(String name) {
		return jdbcTemplate.queryForObject(OrderTypeSql.FIND_BY_NAME, 
				new Object[] { name }, mapper);
	}

    @Override
    public List<OrderType> findAll() {
        return jdbcTemplate.query(OrderTypeSql.FIND_ALL, mapper);
    }
    
}

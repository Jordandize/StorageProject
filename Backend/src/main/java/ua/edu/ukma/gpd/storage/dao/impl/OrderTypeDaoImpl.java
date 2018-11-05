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
    public OrderTypeDaoImpl(DataSource dataSource){ jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<OrderType> mapper = (resultSet, i) -> {
        OrderType orderType = new OrderType();
        orderType.setTypeId(resultSet.getInt("id"));
        orderType.setName(resultSet.getString("name"));
        return orderType;
    };

    @Override
    public Integer create(OrderType orderType) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(OrderTypeSql.INSERT, new String[] {"id"} );
            ps.setString(1, orderType.getName());
            return ps;
        }, keyHolder);
        orderType.setTypeId((Integer) keyHolder.getKey());
        return orderType.getTypeId();
    }

    @Override
    public boolean update(OrderType orderType) {
        return false;
    }

    @Override
    public boolean delete(OrderType orderType) {
        return false;
    }

    @Override
    public List<OrderType> findAll() {
        return null;
    }

   
	@Override
	public OrderType findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}

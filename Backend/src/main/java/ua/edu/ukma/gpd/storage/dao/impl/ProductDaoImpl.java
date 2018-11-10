package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.sql.ProductSql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(DataSource dataSource)
    	{ jdbcTemplate = new JdbcTemplate(dataSource); }

    private RowMapper<Product> mapper = (rs, i) -> {
        Product product = new Product();
        product.setIdCategory(rs.getLong   ("id_category"));
        product.setName      (rs.getString ("name"));
        product.setAmount    (rs.getInt    ("amount"));
        product.setActive    (rs.getBoolean("active"));
        return product;
    };

    @Override
    public Long create(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ProductSql.INSERT,
            		new String[] { "id" });
            ps.setString (1, product.getName());
            ps.setInt    (2, product.getAmount());
            ps.setLong   (3, product.getIdCategory());
            ps.setBoolean(4, product.isActive());
            return ps;
        }, keyHolder);
        product.setId((Long) keyHolder.getKey());
        return product.getId();
    }

    @Override
    public boolean update(Product product) {
        return jdbcTemplate.update(ProductSql.UPDATE,
                product.getName(), product.getAmount(), product.getIdCategory(), 
                product.isActive(), product.getId()) > 0;
    }

    @Override
    public boolean delete(Product product) {
        return jdbcTemplate.update(ProductSql.DELETE, product.getId()) > 0;
    }

    @Override
    public Product findById(Long id) {
        return jdbcTemplate.queryForObject(ProductSql.FIND_BY_ID, 
        		new Object[] { id }, mapper);
    }

    @Override
    public Product findByName(String name) {
        return jdbcTemplate.queryForObject(ProductSql.FIND_BY_NAME, 
        		new Object[] { name }, mapper);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(ProductSql.FIND_ALL, mapper);
    }
    
}

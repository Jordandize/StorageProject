package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.ProductDao;
import ua.edu.ukma.gpd.storage.entity.Category;
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
        
        product.setId         (rs.getLong   ("id"));
        product.setName       (rs.getString ("name"));
        product.setAmount     (rs.getInt    ("amount"));
        product.setActive     (rs.getBoolean("active"));
        product.setDescription(rs.getString ("description"));
        product.setImage      (rs.getString ("image"));
        product.setIcon       (rs.getString ("icon"));
        product.setCategoryId (rs.getLong   ("id_category"));
        
        return product;
    };

    @Override
    public Long create(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ProductSql.INSERT, 
            		new String[]{ "id" });
            
            ps.setString (1, product.getName());
            ps.setInt    (2, product.getAmount());
            ps.setBoolean(3, product.isActive());
            ps.setString (4, product.getDescription());
            ps.setString (5, product.getImage());
            ps.setString (6, product.getIcon());
            ps.setLong   (7, product.getCategoryId());
            
            return ps;
        }, keyHolder);
        product.setId((Long) keyHolder.getKey());
        return product.getId();
    }

    @Override
    public boolean update(Product product) {
        return jdbcTemplate.update(ProductSql.UPDATE,
                product.getName(), product.getAmount(), product.isActive(),
                product.getDescription(), product.getImage(), product.getIcon(),
                product.getCategoryId()) > 0;
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
	public List<Product> findByCategory(Category category) {
		return jdbcTemplate.query(ProductSql.FIND_BY_CATEGORY,
				new Object[] { category.getId() }, mapper);
	}

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(ProductSql.FIND_ALL, mapper);
    }
    
    @Override
    public List<Product> findAllPresent() {
        return jdbcTemplate.query(ProductSql.FIND_ALL_PRESENT, mapper);
    }
    
    @Override
    public List<Product> findAllNotPresent() {
        return jdbcTemplate.query(ProductSql.FIND_ALL_NOT_PRESENT, mapper);
    }
    
    @Override
    public List<Product> findAllEnds(int quantity) {
        return jdbcTemplate.query(ProductSql.FIND_ALL_ENDS, new Object[] { quantity }, mapper);
    }
    
}

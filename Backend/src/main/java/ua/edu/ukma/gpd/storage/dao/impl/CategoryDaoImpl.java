package ua.edu.ukma.gpd.storage.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.gpd.storage.dao.CategoryDao;
import ua.edu.ukma.gpd.storage.entity.Category;
import ua.edu.ukma.gpd.storage.sql.CategorySql;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDaoImpl (DataSource dataSource){ jdbcTemplate = new JdbcTemplate(dataSource);}

    private RowMapper<Category> mapper = (resultSet, i) -> {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    };

    @Override
    public Integer create(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement ps = connection.prepareStatement(CategorySql.INSERT,
                    new String[] {"id"});
            ps.setString(1, category.getName());
            return ps;
                }, keyHolder);
        category.setId((Integer) keyHolder.getKey());
        return category.getId();
    }

    @Override
    public boolean update(Category category) {
        return jdbcTemplate.update(CategorySql.UPDATE, category.getName()) > 0;
    }

    @Override
    public boolean delete(Category category) {
        return jdbcTemplate.update(CategorySql.DELETE, category.getId()) > 0;
    }

    @Override
    public Category findById(Long id) {
        return jdbcTemplate.queryForObject(CategorySql.FIND_BY_ID, new Object[] { id }, mapper);
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(CategorySql.FIND_ALL, mapper);
    }

    @Override
    public Category findByName(String name){
        return jdbcTemplate.queryForObject(CategorySql.FIND_BY_NAME, new Object[] {name}, mapper);
    }
}

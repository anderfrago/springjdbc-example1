package org.cuatrovientos.jdbc.persistence.repository;

import java.util.List;

import javax.sql.DataSource;

import org.cuatrovientos.jdbc.persistence.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

	 private JdbcTemplate jdbcTemplate;
	 
	 public CategoryRepository(final DataSource dataSource){
		 this.jdbcTemplate = new JdbcTemplate(dataSource);
	 }


	public int save(Category category) {
		return jdbcTemplate.update("INSERT INTO category (id,name) values(?,?)", category.getId(), category.getName());
	}

	public List<Category> findAll() {
		return jdbcTemplate.query("select * from category",
				(rs, rowNum) -> new Category(rs.getLong("id"), rs.getString("name")));
	}

	public List<Category> findById(Long id) {
		return jdbcTemplate.query("select * from category where id = ?",
				(rs, rowNum) -> new Category(rs.getLong("id"), rs.getString("name")), id);
	}

	public int update(Category category) {
		return jdbcTemplate.update("UPDATE category SET name = ? WHERE id = ?", category.getName(), category.getId());
	}

	public int delete(Long id) {
		return jdbcTemplate.update("DELETE category WHERE id = ?", id);
	}

}

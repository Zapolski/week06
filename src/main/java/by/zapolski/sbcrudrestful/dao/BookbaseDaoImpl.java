package by.zapolski.sbcrudrestful.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookbaseDaoImpl extends JdbcDaoSupport implements BookbaseDao{
	
	private String sql = "SELECT b.name as \"Book Title\", b.creation_year as \"Creation Year\", g.genre as \"Genre\", a.name as \"Author\""
			+ "FROM books b JOIN genres g ON g.id=b.genre_id"
			+ " JOIN authors a ON a.id=b.author_id where ";	

	@Autowired
	public BookbaseDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public List<Map<String, Object>> queryForBooks(String partQuery) {
		System.out.println(sql+partQuery);
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql+partQuery);
		return list;
	}
}

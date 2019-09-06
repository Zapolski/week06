package by.zapolski.sbcrudrestful.dao;

import java.util.List;

import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class BookbaseDAO extends JdbcDaoSupport{
	
	@Autowired
	public BookbaseDAO (DataSource dataSource) {
		 this.setDataSource(dataSource);
	}
	
    public List<String> getGenNames() {
        String sql = "Select g.genre from genres g";
        // queryForList(String sql, Class<T> elementType)
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }
 
    public List<String> getGenNames(String searchName) {
        String sql = "Select g.genre from genres g "//
                + " Where g.genre like ? ";
        // queryForList(String sql, Class<T> elementType, Object... args)
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class, //
                "%" + searchName + "%");
        return list;
    }	

}

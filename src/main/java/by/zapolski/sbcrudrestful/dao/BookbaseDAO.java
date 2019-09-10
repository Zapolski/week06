package by.zapolski.sbcrudrestful.dao;

import java.util.List;
import java.util.Map;

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
    
    // Map<String columnName, Object value>
    public List<Map<String, Object>> queryForBooks() {
 
        String sql = "SELECT b.name as \"Book Title\", b.creation_year as \"Creation Year\", g.genre as \"Genre\", a.name as \"Author\"\r\n" + 
        		"FROM books b 	JOIN genres g ON g.id=b.genre_id\r\n" + 
        		"				JOIN authors a ON a.id=b.author_id\r\n" + 
        		"WHERE a.name = 'Pushkin' or a.name='Lermontov' or g.genre = 'Detective' or b.creation_year>=1833 AND b.creation_year<=1837";
        		//"WHERE a.name = 'Gogol'";
 
        // List<Map<String, Object>> queryForList(String sql)
        List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
 
        return list;
    }
     

}

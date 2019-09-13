package by.zapolski.sbcrudrestful.dao;

import java.util.List;
import java.util.Map;

public interface BookbaseDao {
	List<Map<String, Object>> queryForBooks(String partQuery); 
}

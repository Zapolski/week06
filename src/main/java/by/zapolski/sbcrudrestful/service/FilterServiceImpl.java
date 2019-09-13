package by.zapolski.sbcrudrestful.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.zapolski.sbcrudrestful.dao.BookbaseDao;

@Service
public class FilterServiceImpl implements FilterService{

	@Autowired
	private BookbaseDao bookbaseDAO;
	
	private String and;
	
	@Override
	public List<Map<String, Object>> queryForBooks(String[] author, String[] genre, Integer from, Integer to) {
		StringBuilder sb = new StringBuilder();
		and = "";
		sb.append(getArrayCondition(author, "a.name"));
		sb.append(getArrayCondition(genre, "g.genre"));
		sb.append(getContition(from, "b.creation_year", ">="));
		sb.append(getContition(to, "b.creation_year", "<="));
		
		System.out.println(sb.toString());
		return bookbaseDAO.queryForBooks(sb.toString());
	}
	
	private String getArrayCondition(String[] array, String conditionField) {
		StringBuilder sb = new StringBuilder("");
		if (array != null) {
			sb.append(and).append("(");
			for (String str : array) {
				sb.append(conditionField).append("='").append(str).append("' or ");
			}
			sb.delete(sb.length()-" or ".length(),sb.length());
			sb.append(")");
			and= " and ";
		}
		return sb.toString();
	}
	
	private String getContition(Integer value, String conditionField, String condition) {
		StringBuilder sb = new StringBuilder("");
		if (value != null) {
			sb.append(and).append("(");
			sb.append(conditionField).append(condition);
			sb.append(value);
			sb.append(")");
			and= " and ";
		}		
		return sb.toString();		
	}
}

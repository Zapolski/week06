package by.zapolski.sbcrudrestful.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.zapolski.sbcrudrestful.dao.BookbaseDAO;

@Service
public class FilterService {

	@Autowired
	private BookbaseDAO bookbaseDAO;
	
	public List<Map<String, Object>> queryForBooks(String[] author, String[] genre, Integer from, Integer to) {
		StringBuilder sb = new StringBuilder();
		String and = "";
		
		if (author != null) {
			sb.append(and+"(");
			for (String str : author) {
				sb.append("a.name='").append(str).append("' or ");
			}
			sb.delete(sb.length()-" or ".length(),sb.length());
			sb.append(")");
			and= " and ";
		}
		
		if (genre != null) {
			sb.append(and+"(");
			for (String str : genre) {
				sb.append("g.genre='").append(str).append("' or ");
			}
			sb.delete(sb.length()-" or ".length(),sb.length());
			sb.append(")");
			and= " and ";
		}
		
		if (from != null) {
			sb.append(and+"(");
			sb.append("b.creation_year>=");
			sb.append(from);
			sb.append(")");
			and= " and ";
		}
		
		if (to != null) {
			sb.append(and+"(");
			sb.append("b.creation_year<=");
			sb.append(to);
			sb.append(")");
			and= " and ";
		}
		System.out.println(sb.toString());
		return bookbaseDAO.queryForBooks(sb.toString());
	}
}

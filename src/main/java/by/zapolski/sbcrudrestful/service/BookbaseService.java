package by.zapolski.sbcrudrestful.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.zapolski.sbcrudrestful.dao.BookbaseDAO;

@Service
public class BookbaseService {

	@Autowired
	private BookbaseDAO bookbaseDAO;
	
	public List<Map<String, Object>> queryForBooks(String[] author, String[] genre, Integer from, Integer to) {
		
		StringBuilder sb = new StringBuilder();
		
		if (author != null) {
			for (String str : author) {
				
			}
		}
		
		
		return bookbaseDAO.queryForBooks(author, genre, from, to);
	}

}

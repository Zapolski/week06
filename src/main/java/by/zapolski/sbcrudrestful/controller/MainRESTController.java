package by.zapolski.sbcrudrestful.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.zapolski.sbcrudrestful.dao.BookbaseDAO;

@RestController
public class MainRESTController {
	
	@Autowired
	private BookbaseDAO bookbaseDAO;
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Hello from my Mini Rest Service";
	}
	
	@RequestMapping(value="/books", produces="application/csv")
	public void csvFile(@RequestParam(value = "author", required = false) String[] author, 
						@RequestParam(value = "genre", required = false) String[] genre,
						@RequestParam(value = "from", required = false) String from,
						@RequestParam(value = "to", required = false) String to,
						HttpServletResponse response) throws IOException {
		
		if ( author==null && genre==null && from==null && to==null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setContentType("text/html");
			response.getWriter().write("At least one filter parameter required");
			return;
		}
		
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=\"books.csv\"");
	    
	    List<Map<String,Object>> list = bookbaseDAO.queryForBooks(author, genre, from, to);
//		for (Map<String, Object> map : list) {
//			System.out.println(map);
//		}
	    
	    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
	    
	    String[] header = list.get(0).keySet().toArray(new String[0]);
	    CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader(header));
		for (Map<String, Object> map : list) {
			List<String> row = new ArrayList<>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				row.add(entry.getValue().toString());
			}
			csvPrinter.printRecord(row.toArray());
		}
		csvPrinter.flush();
		csvPrinter.close();
		
		bufferedWriter.close();
	}
	
}


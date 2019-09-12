package by.zapolski.sbcrudrestful.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.zapolski.sbcrudrestful.service.CsvService;
import by.zapolski.sbcrudrestful.service.FilterService;

@RestController
public class MainRESTController {
	
	@Autowired
	private FilterService filterService;
	
	@Autowired
	private CsvService csvService;	
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Hello from Mini CSV Rest Service";
	}
	
	@RequestMapping(value="/books")
	public ResponseEntity<?> csvFile2(@RequestParam(value = "author", required = false) String[] author, 
						@RequestParam(value = "genre", required = false) String[] genre,
						@RequestParam(value = "from", required = false) Integer from,
						@RequestParam(value = "to", required = false) Integer to) throws IOException {
		
		if ( author==null && genre==null && from==null && to==null) {
		       return ResponseEntity.badRequest()
		               				.body("At least one filter parameter required");			
		}
		
	    List<Map<String,Object>> list = filterService.queryForBooks(author, genre, from, to);
	    
	    ByteArrayOutputStream byteArrayOutputStream = csvService.getOutput(list); 
	    
		return ResponseEntity.ok()
							 .contentType(MediaType.parseMediaType("text/csv"))
							 .header("Content-Disposition", "attachment; filename=\"books.csv\"")
							 .body(byteArrayOutputStream.toByteArray());		
	}	
}


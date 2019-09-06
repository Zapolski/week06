package by.zapolski.sbcrudrestful.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import by.zapolski.sbcrudrestful.dao.BookbaseDAO;

@RestController
public class MainRESTController {
	
	@Autowired
	private BookbaseDAO queryForList;
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Hello from my Mini Rest Service";
	}
	
	@RequestMapping(value="/zip", produces="application/zip")
	public void zipFiles(HttpServletResponse response) throws IOException {

	    //setting headers  
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

	    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

	    // create a list to add files to be zipped
	    ArrayList<File> files = new ArrayList<>(2);
	    files.add(new File("database.txt"));

	    // package files
	    for (File file : files) {
	        //new zip entry and copying inputstream with file to zipOutputStream, after all closing streams
	        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
	        FileInputStream fileInputStream = new FileInputStream(file);

	        IOUtils.copy(fileInputStream, zipOutputStream);

	        fileInputStream.close();
	        zipOutputStream.closeEntry();
	    }    

	    zipOutputStream.close();
	}
	
	
	
}


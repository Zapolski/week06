package by.zapolski.sbcrudrestful.service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

@Service
public class CsvServiceImpl implements CsvService{

	@Override
	public ByteArrayOutputStream getOutput(List<Map<String, Object>> list) throws IOException {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
	    
	    String[] header = new String[0];
	    if (!list.isEmpty()) {
		    header = list.get(0).keySet().toArray(new String[0]);	    	
	    }

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
		
		return byteArrayOutputStream;

	}

}

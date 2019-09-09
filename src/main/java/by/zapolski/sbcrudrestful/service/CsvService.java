package by.zapolski.sbcrudrestful.service;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Repository;

@Repository
public class CsvService {

	public static void main(String[] args) throws IOException {
		String SAMPLE_CSV_FILE = "./sample.csv";
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

				CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("ID", "Name", "Designation", "Company"));) {
			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

			csvPrinter.flush();
		}

	}

	public static BufferedWriter getCsvStream(List<Map<String, Object>> list) throws IOException {
		String[] header = (String[]) list.get(0).keySet().toArray();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new ByteArrayOutputStream()));
		CSVPrinter csvPrinter = new CSVPrinter(bw, CSVFormat.DEFAULT.withHeader(header));
		for (Map<String, Object> map : list) {
			List<String> row = new ArrayList<>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				row.add(entry.getValue().toString());
			}
			csvPrinter.printRecord(row.toArray());
		}
		csvPrinter.flush();
		//csvPrinter.close();
		return bw;
	}

}

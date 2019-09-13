package by.zapolski.sbcrudrestful.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CsvService {
	ByteArrayOutputStream getOutput(List<Map<String, Object>> list) throws IOException;
}

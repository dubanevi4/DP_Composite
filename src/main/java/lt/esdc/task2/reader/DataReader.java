package lt.esdc.task2.reader;

import lt.esdc.task2.exception.ProjectException;

import java.io.*;
import java.util.stream.Collectors;

public class DataReader {
    public String readText(String filename) throws ProjectException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (is == null) {
                throw new ProjectException("Resource not found: " + filename);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                return br.lines()
                        .collect(Collectors.joining("\n"));
            }
        } catch (IOException e) {
            throw new ProjectException("Error reading resource: " + filename, e);
        }
    }
}

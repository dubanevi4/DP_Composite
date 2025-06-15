package lt.esdc.task2.reader;

import lt.esdc.task2.exception.ProjectException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    public String readText(String filename) throws ProjectException {
        File file;
        try {
            file = new File(getClass().getClassLoader().getResource(filename).getFile());
            return readTextFromFile(file);
        } catch (NullPointerException e) {
            throw new ProjectException("Problem with the path to file: " + filename, e);
        }
    }

    private String readTextFromFile(File path) throws ProjectException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines()
              .filter(line -> !line.isEmpty())
              .forEachOrdered(s -> sb.append(s).append("\n"));
            return sb.toString().strip();
        } catch (IOException e) {
            throw new ProjectException("Error reading file: " + path.getName(), e);
        }
    }
}

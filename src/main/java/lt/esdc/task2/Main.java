package lt.esdc.task2;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.operation.TextOperation;
import lt.esdc.task2.operation.impl.CountDuplicateWords;
import lt.esdc.task2.operation.impl.CountVowelsAndConsonants;
import lt.esdc.task2.operation.impl.FindSentencesWithLongestWord;
import lt.esdc.task2.operation.impl.RemoveShortSentences;
import lt.esdc.task2.operation.impl.SortParagraphsBySentenceCount;
import lt.esdc.task2.parser.AbstractParser;
import lt.esdc.task2.parser.ParserChainBuilder;
import lt.esdc.task2.util.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final String INPUT_FILE = "src/main/resources/input.txt";
    private static final String OUTPUT_DIR = "output";

    public static void main(String[] args) {
        try {
            Logger.info("Starting text processing application");
            
            // Create output directory if it doesn't exist
            createOutputDirectory();
            
            // Read text from file
            String inputText = readTextFromFile(INPUT_FILE);
            Logger.info("Successfully read input text from file");
            
            // Parse text into composite structure
            AbstractParser parser = ParserChainBuilder.buildParserChain();
            TextComponent text = parser.parse(inputText);
            Logger.info("Successfully parsed text into composite structure");
            
            // Recover text from composite structure
            String recoveredText = text.toString();
            Logger.info("Successfully recovered text from composite structure");
            
            // Write recovered text to file
            writeTextToFile(OUTPUT_DIR + "/recovered.txt", recoveredText);
            Logger.info("Successfully wrote recovered text to file");
            
            // Perform text operations
            performTextOperations(text);
            
            Logger.info("Text processing completed successfully");
            
        } catch (Exception e) {
            Logger.error("Error occurred during text processing", e);
        }
    }
    
    private static void createOutputDirectory() throws IOException {
        Path outputPath = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outputPath)) {
            Logger.info("Creating output directory: " + OUTPUT_DIR);
            Files.createDirectory(outputPath);
        }
    }
    
    private static String readTextFromFile(String filename) throws IOException {
        Logger.info("Reading text from file: " + filename);
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            throw new IOException("Input file not found: " + filename);
        }
        String content = Files.readString(path);
        Logger.debug("Read " + content.length() + " characters from file");
        return content;
    }
    
    private static void writeTextToFile(String filename, String content) throws IOException {
        Logger.info("Writing text to file: " + filename);
        Path path = Paths.get(filename);
        Files.writeString(path, content);
        Logger.debug("Wrote " + content.length() + " characters to file");
    }
    
    private static void performTextOperations(TextComponent text) throws IOException {
        Logger.info("Starting text operations");
        
        // Sort paragraphs by sentence count
        TextOperation sortOperation = new SortParagraphsBySentenceCount();
        TextComponent sortedText = sortOperation.execute(text);
        writeTextToFile(OUTPUT_DIR + "/sorted_paragraphs.txt", sortedText.toString());
        Logger.info("Completed sorting paragraphs by sentence count");
        
        // Find sentences with longest word
        TextOperation longestWordOperation = new FindSentencesWithLongestWord();
        TextComponent longestWordText = longestWordOperation.execute(text);
        writeTextToFile(OUTPUT_DIR + "/longest_word_sentences.txt", longestWordText.toString());
        Logger.info("Completed finding sentences with longest word");
        
        // Remove short sentences
        TextOperation removeShortOperation = new RemoveShortSentences(3);
        TextComponent shortRemovedText = removeShortOperation.execute(text);
        writeTextToFile(OUTPUT_DIR + "/short_removed.txt", shortRemovedText.toString());
        Logger.info("Completed removing short sentences");
        
        // Count duplicate words
        TextOperation duplicateOperation = new CountDuplicateWords();
        TextComponent duplicateCount = duplicateOperation.execute(text);
        writeTextToFile(OUTPUT_DIR + "/duplicate_words.txt", duplicateCount.toString());
        Logger.info("Completed counting duplicate words");
        
        // Count vowels and consonants
        TextOperation vowelConsonantOperation = new CountVowelsAndConsonants();
        TextComponent vowelConsonantCount = vowelConsonantOperation.execute(text);
        writeTextToFile(OUTPUT_DIR + "/vowel_consonant_count.txt", vowelConsonantCount.toString());
        Logger.info("Completed counting vowels and consonants");
    }
}
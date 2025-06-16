package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Paragraph;
import lt.esdc.task2.composite.impl.Text;
import lt.esdc.task2.operation.TextOperation;
import lt.esdc.task2.util.Logger;

public class FindSentencesWithLongestWord implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        if (!(text instanceof Text)) {
            Logger.warn("Input is not a Text component, returning as is");
            return text;
        }

        Text result = new Text();
        String longestWord = findLongestWord(text);
        Logger.info("Found longest word: " + longestWord);
        
        for (TextComponent paragraph : text.getComponents()) {
            Paragraph newParagraph = new Paragraph();
            boolean hasLongestWord = false;
            
            for (TextComponent sentence : paragraph.getComponents()) {
                String sentenceText = sentence.toString();
                if (sentenceText.contains(longestWord)) {
                    newParagraph.add(sentence);
                    hasLongestWord = true;
                }
            }
            
            if (hasLongestWord) {
                result.add(newParagraph);
            }
        }
        
        Logger.info("Found " + result.getComponents().size() + " paragraphs containing the longest word");
        return result;
    }

    private String findLongestWord(TextComponent text) {
        String longestWord = "";
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent lexeme : sentence.getComponents()) {
                    String word = lexeme.toString().strip();
                    /* Skip punctuation and empty strings */
                    if (!word.isEmpty() && !word.matches("^[.,!?]+$")) {
                        if (word.length() > longestWord.length()) {
                            longestWord = word;
                        }
                    }
                }
            }
        }
        return longestWord;
    }
} 
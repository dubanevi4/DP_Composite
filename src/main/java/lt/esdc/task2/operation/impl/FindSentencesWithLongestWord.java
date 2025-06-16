package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Paragraph;
import lt.esdc.task2.composite.impl.Text;
import lt.esdc.task2.operation.TextOperation;

import java.util.ArrayList;
import java.util.List;

public class FindSentencesWithLongestWord implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        if (!(text instanceof Text)) {
            return text;
        }

        Text result = new Text();
        String longestWord = findLongestWord(text);
        
        for (TextComponent paragraph : text.getComponents()) {
            Paragraph newParagraph = new Paragraph();
            boolean hasLongestWord = false;
            
            for (TextComponent sentence : paragraph.getComponents()) {
                if (sentence.toString().contains(longestWord)) {
                    newParagraph.add(sentence);
                    hasLongestWord = true;
                }
            }
            
            if (hasLongestWord) {
                result.add(newParagraph);
            }
        }
        
        return result;
    }

    private String findLongestWord(TextComponent text) {
        String longestWord = "";
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent lexeme : sentence.getComponents()) {
                    String word = lexeme.toString();
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
        }
        return longestWord;
    }
} 
package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
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
        int maxWordLength = findMaxWordLength(text);
        
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                if (containsWordOfLength(sentence, maxWordLength)) {
                    result.add(sentence);
                }
            }
        }
        
        return result;
    }

    private int findMaxWordLength(TextComponent text) {
        int maxLength = 0;
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent lexeme : sentence.getComponents()) {
                    maxLength = Math.max(maxLength, lexeme.toString().length());
                }
            }
        }
        return maxLength;
    }

    private boolean containsWordOfLength(TextComponent sentence, int length) {
        for (TextComponent lexeme : sentence.getComponents()) {
            if (lexeme.toString().length() == length) {
                return true;
            }
        }
        return false;
    }
} 
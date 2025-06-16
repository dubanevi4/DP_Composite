package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Paragraph;
import lt.esdc.task2.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final String SENTENCE_DELIMITER = "([.!?])\\s*";

    @Override
    public TextComponent parse(String text) {
        Paragraph paragraph = new Paragraph();
        Pattern pattern = Pattern.compile(SENTENCE_DELIMITER);
        Matcher matcher = pattern.matcher(text);
        
        int lastEnd = 0;
        while (matcher.find()) {
            String sentenceText = text.substring(lastEnd, matcher.end()).trim();
            if (!sentenceText.isEmpty()) {
                TextComponent sentence = parseNext(sentenceText);
                if (sentence != null) {
                    paragraph.add(sentence);
                }
            }
            lastEnd = matcher.end();
        }
        
        // Handle the last sentence if there's no punctuation at the end
        if (lastEnd < text.length()) {
            String sentenceText = text.substring(lastEnd).trim();
            if (!sentenceText.isEmpty()) {
                TextComponent sentence = parseNext(sentenceText);
                if (sentence != null) {
                    paragraph.add(sentence);
                }
            }
        }
        
        return paragraph;
    }
}

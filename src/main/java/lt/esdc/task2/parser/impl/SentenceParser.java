package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Sentence;
import lt.esdc.task2.parser.AbstractParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {
    private static final String LEXEME_DELIMITER = " ";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("([^.!?]+[.!?])\\s*");

    @Override
    public TextComponent parse(String text) {
        Sentence sentence = new Sentence();
        Matcher matcher = SENTENCE_PATTERN.matcher(text);
        
        if (matcher.find()) {
            String sentenceText = matcher.group(1).trim();
            String[] lexemes = sentenceText.split(LEXEME_DELIMITER);
            
            for (String lexemeText : lexemes) {
                if (!lexemeText.isEmpty()) {
                    TextComponent lexeme = parseNext(lexemeText);
                    if (lexeme != null) {
                        sentence.add(lexeme);
                    }
                }
            }
        }
        
        return sentence;
    }
}

package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Sentence;
import lt.esdc.task2.parser.AbstractParser;

public class SentenceParser extends AbstractParser {
    private static final String LEXEME_DELIMITER = "\\s+";

    @Override
    public TextComponent parse(String text) {
        Sentence sentence = new Sentence();
        String[] lexemes = text.split(LEXEME_DELIMITER);
        
        for (String lexemeText : lexemes) {
            if (!lexemeText.isEmpty()) {
                TextComponent lexeme = parseNext(lexemeText);
                if (lexeme != null) {
                    sentence.add(lexeme);
                }
            }
        }
        
        return sentence;
    }
}

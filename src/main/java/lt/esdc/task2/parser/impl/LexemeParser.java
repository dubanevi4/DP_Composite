package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Lexeme;
import lt.esdc.task2.parser.AbstractParser;

public class LexemeParser extends AbstractParser {
    @Override
    public TextComponent parse(String text) {
        Lexeme lexeme = new Lexeme();
        for (char c : text.toCharArray()) {
            TextComponent symbol = parseNext(String.valueOf(c));
            if (symbol != null) {
                lexeme.add(symbol);
            }
        }
        return lexeme;
    }
}

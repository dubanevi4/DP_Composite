package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Symbol;
import lt.esdc.task2.parser.AbstractParser;

public class SymbolParser extends AbstractParser {
    @Override
    public TextComponent parse(String text) {
        if (text.length() == 1) {
            return new Symbol(text.charAt(0));
        }
        return null;
    }
}

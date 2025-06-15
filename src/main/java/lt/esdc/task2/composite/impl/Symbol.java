package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.exception.ProjectException;

import java.util.List;

public class Symbol implements TextComponent {
    private SymbolType type;
    private char content;

    public Symbol(char content) {
        this.content = content;
        this.type = determineSymbolType(content);
    }

    private SymbolType determineSymbolType(char c) {
        if (Character.isLetter(c)) {
            return SymbolType.LETTER;
        } else if (Character.isDigit(c)) {
            return SymbolType.DIGIT;
        } else if (c == '.' || c == '!' || c == '?' || c == ';' || c == ':' || c == ',') {
            return SymbolType.PUNCTUATION;
        } else {
            return SymbolType.OTHER;
        }
    }

    public SymbolType getSymbolType() {
        return type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Method is not supported for Symbol");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Method is not supported for Symbol");
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException("Method is not supported for Symbol");
    }

    @Override
    public TextComponentType getComponentType() {
        return TextComponentType.SYMBOL;
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Method is not supported for Symbol");
    }

    @Override
    public String toString() {
        return Character.toString(content);
    }
}

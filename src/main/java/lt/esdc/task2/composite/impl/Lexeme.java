package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;

public class Lexeme extends TextComposite {
    public Lexeme() {
        this.type = TextComponentType.LEXEME;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : components) {
            result.append(component.toString());
        }
        return result.toString();
    }
} 
package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;

public class Sentence extends TextComposite {
    public Sentence() {
        this.type = TextComponentType.SENTENCE;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            result.append(component.toString());
            // Add word delimiter after lexeme if it's not the last component and the next component is not punctuation
            if (i < components.size() - 1) {
                TextComponent nextComponent = components.get(i + 1);
                if (nextComponent instanceof Lexeme) {
                    result.append(TextConstants.WORD_DELIMITER);
                }
            }
        }
        return result.toString();
    }
} 
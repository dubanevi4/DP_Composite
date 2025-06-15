package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;

public class Paragraph extends TextComposite {
    public Paragraph() {
        this.type = TextComponentType.PARAGRAPH;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            result.append(component.toString());
            // Add sentence delimiter after each sentence except the last one
            if (i < components.size() - 1) {
                result.append(TextConstants.SENTENCE_DELIMITER);
            }
        }
        return result.toString();
    }
} 
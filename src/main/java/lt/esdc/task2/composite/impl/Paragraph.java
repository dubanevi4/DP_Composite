package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.TextComposite;

public class Paragraph extends TextComposite {
    public Paragraph() {
        this.type = TextComponentType.PARAGRAPH;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            String componentText = component.toString();
            result.append(componentText);
            // Add sentence delimiter after each sentence except the last one
            if (i < components.size() - 1) {
                // Only add the space part of the delimiter if the sentence doesn't end with punctuation
                if (!componentText.matches(".*[.!?]\\s*$")) {
                    result.append(Text.Delimiter.SENTENCE.getValue());
                } else {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }
} 
package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;

public class Text extends TextComposite {
    public Text() {
        this.type = TextComponentType.TEXT;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            result.append(component.toString());
            // Add paragraph delimiter after each paragraph except the last one
            if (i < components.size() - 1) {
                result.append(TextConstants.PARAGRAPH_DELIMITER);
            }
        }
        return result.toString();
    }
} 
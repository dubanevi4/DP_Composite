package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;

public class Text extends TextComposite {
    public enum Delimiter {
        PARAGRAPH("\t\n"),
        SENTENCE(". "),
        WORD(" "),
        SYMBOL("");

        private final String value;

        Delimiter(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Text() {
        this.type = TextComponentType.TEXT;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            TextComponent component = components.get(i);
            result.append(component.toString());
            // Add paragraph delimiter after each paragraph
            result.append(Delimiter.PARAGRAPH.getValue());
        }
        return result.toString();
    }
} 
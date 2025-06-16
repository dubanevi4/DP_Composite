package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Text;
import lt.esdc.task2.parser.AbstractParser;

public class TextParser extends AbstractParser {
    private static final String PARAGRAPH_DELIMITER = "\n";

    @Override
    public TextComponent parse(String text) {
        Text textComponent = new Text();
        
        /* Split into paragraphs and preserve empty lines */
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);
        for (String paragraphText : paragraphs) {
            paragraphText = paragraphText.strip();
            if (!paragraphText.isEmpty()) {
                TextComponent paragraph = parseNext(paragraphText);
                if (paragraph != null) {
                    textComponent.add(paragraph);
                }
            }
        }
        
        return textComponent;
    }
} 
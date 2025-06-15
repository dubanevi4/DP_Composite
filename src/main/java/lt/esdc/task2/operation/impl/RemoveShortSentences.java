package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Text;
import lt.esdc.task2.operation.TextOperation;

public class RemoveShortSentences implements TextOperation {
    private final int minWordCount;

    public RemoveShortSentences(int minWordCount) {
        this.minWordCount = minWordCount;
    }

    @Override
    public TextComponent execute(TextComponent text) {
        if (!(text instanceof Text)) {
            return text;
        }

        Text result = new Text();
        
        for (TextComponent paragraph : text.getComponents()) {
            TextComponent newParagraph = createNewParagraph(paragraph);
            for (TextComponent sentence : paragraph.getComponents()) {
                if (sentence.getComponents().size() >= minWordCount) {
                    newParagraph.add(sentence);
                }
            }
            if (!newParagraph.getComponents().isEmpty()) {
                result.add(newParagraph);
            }
        }
        
        return result;
    }

    private TextComponent createNewParagraph(TextComponent originalParagraph) {
        try {
            return originalParagraph.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // If we can't create a new instance, return the original paragraph
            return originalParagraph;
        }
    }
} 
package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.Paragraph;
import lt.esdc.task2.composite.impl.Text;
import lt.esdc.task2.operation.TextOperation;

import java.util.Comparator;
import java.util.List;

public class SortParagraphsBySentenceCount implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        Text result = new Text();
        List<TextComponent> paragraphs = text.getComponents();
        paragraphs.sort(Comparator.comparingInt(p -> p.getComponents().size()));
        
        for (TextComponent paragraph : paragraphs) {
            Paragraph newParagraph = new Paragraph();
            // Copy all sentences from the original paragraph
            for (TextComponent sentence : paragraph.getComponents()) {
                newParagraph.add(sentence);
            }
            result.add(newParagraph);
        }
        
        return result;
    }
} 
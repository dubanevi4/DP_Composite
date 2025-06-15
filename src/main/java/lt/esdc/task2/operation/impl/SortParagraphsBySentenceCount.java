package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
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
            result.add(paragraph);
        }
        
        return result;
    }
} 
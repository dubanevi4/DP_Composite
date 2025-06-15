package lt.esdc.task2.composite;

import lt.esdc.task2.composite.impl.TextComponentType;
import java.util.List;

public interface TextComponent {

    void add (TextComponent component);

    void remove(TextComponent component);

    TextComponent getChild(int index);

    TextComponentType getComponentType();

    List<TextComponent> getComponents();

    String toString();
}

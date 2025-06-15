package lt.esdc.task2.composite.impl;

import lt.esdc.task2.composite.TextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class TextComposite implements TextComponent {
    protected List<TextComponent> components = new ArrayList<>();
    protected TextComponentType type;

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public TextComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public TextComponentType getComponentType() {
        return type;
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : components) {
            result.append(component.toString());
        }
        return result.toString();
    }
}

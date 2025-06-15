package lt.esdc.task2.parser;

import lt.esdc.task2.composite.TextComponent;
import java.util.List;

public abstract class AbstractParser {
    protected AbstractParser nextParser;

    public void setNextParser(AbstractParser nextParser) {
        this.nextParser = nextParser;
    }

    protected TextComponent parseNext(String text) {
        if (nextParser != null) {
            return nextParser.parse(text);
        }
        return null;
    }

    public abstract TextComponent parse(String text);
} 
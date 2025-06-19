package lt.esdc.task2.interpreter;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;

import java.util.List;

public class ArithmeticExpression implements TextComponent {
    private final String expression;
    private final Expression parsedExpression;
    private final double result;

    public ArithmeticExpression(String expression) {
        this.expression = expression;
        InfixArithmeticParser parser = new InfixArithmeticParser();
        this.parsedExpression = parser.parse(expression);
        this.result = this.parsedExpression.interpret();
    }

    public double getResult() {
        return result;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Method is not supported for ArithmeticExpression");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Method is not supported for ArithmeticExpression");
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException("Method is not supported for ArithmeticExpression");
    }

    @Override
    public TextComponentType getComponentType() {
        return TextComponentType.LEXEME; // Обрабатываем как лексему
    }

    @Override
    public List<TextComponent> getComponents() {
        throw new UnsupportedOperationException("Method is not supported for ArithmeticExpression");
    }

    @Override
    public String toString() {
        return String.valueOf(result);
    }

    public String getOriginalExpression() {
        return expression;
    }
} 
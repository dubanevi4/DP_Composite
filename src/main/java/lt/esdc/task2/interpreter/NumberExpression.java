package lt.esdc.task2.interpreter;

public class NumberExpression implements Expression {
    private final double number;

    public NumberExpression(double number) {
        this.number = number;
    }

    @Override
    public double interpret() {
        return number;
    }
} 
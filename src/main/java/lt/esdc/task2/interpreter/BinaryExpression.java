package lt.esdc.task2.interpreter;

public class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final BinaryOperator operator;

    public BinaryExpression(Expression left, Expression right, BinaryOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public double interpret() {
        return operator.apply(left.interpret(), right.interpret());
    }
} 
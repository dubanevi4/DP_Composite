package lt.esdc.task2.interpreter;

@FunctionalInterface
public interface BinaryOperator {
    double apply(double left, double right);
} 
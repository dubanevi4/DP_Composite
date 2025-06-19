package lt.esdc.task2.interpreter;

public enum ArithmeticOperator {
    ADD("+", (a, b) -> a + b),
    SUBTRACT("-", (a, b) -> a - b),
    MULTIPLY("*", (a, b) -> a * b),
    DIVIDE("/", (a, b) -> {
        if (b == 0) throw new ArithmeticException(ErrorMessages.DIVISION_BY_ZERO);
        return a / b;
    });

    private static final String UNKNOWN_OPERATOR_MESSAGE = "Unknown operator: ";
    
    private final String symbol;
    private final BinaryOperator operation;

    ArithmeticOperator(String symbol, BinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public String getSymbol() {
        return symbol;
    }

    public BinaryOperator getOperation() {
        return operation;
    }

    public static ArithmeticOperator fromSymbol(String symbol) {
        for (ArithmeticOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException(UNKNOWN_OPERATOR_MESSAGE + symbol);
    }

    private static class ErrorMessages {
        private static final String DIVISION_BY_ZERO = "Division by zero";
    }
} 
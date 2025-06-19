package lt.esdc.task2.interpreter;

import java.util.Stack;
import java.util.regex.Pattern;

public class InfixArithmeticParser {

    private static final char ADDITION_OPERATOR = '+';
    private static final char SUBTRACTION_OPERATOR = '-';
    private static final char MULTIPLICATION_OPERATOR = '*';
    private static final char DIVISION_OPERATOR = '/';
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    private static final int ADDITION_SUBTRACTION_PRECEDENCE = 1;
    private static final int MULTIPLICATION_DIVISION_PRECEDENCE = 2;
    private static final int DEFAULT_PRECEDENCE = 0;

    private static final String DIVISION_BY_ZERO_MESSAGE = "Division by zero";
    private static final String UNKNOWN_OPERATOR_MESSAGE = "Unknown operator: ";

    public Expression parse(String expression) {
        String cleanExpression = expression.replaceAll("\\s+", "");
        return parseExpression(cleanExpression);
    }

    private Expression parseExpression(String expression) {
        Stack<Expression> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                // Читаем число
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;
                values.push(new NumberExpression(Double.parseDouble(num.toString())));
            } else if (c == OPEN_BRACKET) {
                operators.push(c);
            } else if (c == CLOSE_BRACKET) {
                while (!operators.isEmpty() && operators.peek() != OPEN_BRACKET) {
                    applyOperator(values, operators.pop());
                }
                if (!operators.isEmpty() && operators.peek() == OPEN_BRACKET) {
                    operators.pop();
                }
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && operators.peek() != OPEN_BRACKET && 
                       getPrecedence(operators.peek()) >= getPrecedence(c)) {
                    applyOperator(values, operators.pop());
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators.pop());
        }

        return values.pop();
    }

    private void applyOperator(Stack<Expression> values, char operator) {
        Expression right = values.pop();
        Expression left = values.pop();
        
        BinaryOperator binaryOperator = getBinaryOperator(operator);
        values.push(new BinaryExpression(left, right, binaryOperator));
    }

    private boolean isOperator(char c) {
        return c == ADDITION_OPERATOR || c == SUBTRACTION_OPERATOR || 
               c == MULTIPLICATION_OPERATOR || c == DIVISION_OPERATOR;
    }

    private int getPrecedence(char operator) {
        switch (operator) {
            case ADDITION_OPERATOR:
            case SUBTRACTION_OPERATOR:
                return ADDITION_SUBTRACTION_PRECEDENCE;
            case MULTIPLICATION_OPERATOR:
            case DIVISION_OPERATOR:
                return MULTIPLICATION_DIVISION_PRECEDENCE;
            default:
                return DEFAULT_PRECEDENCE;
        }
    }

    private BinaryOperator getBinaryOperator(char operator) {
        switch (operator) {
            case ADDITION_OPERATOR:
                return (a, b) -> a + b;
            case SUBTRACTION_OPERATOR:
                return (a, b) -> a - b;
            case MULTIPLICATION_OPERATOR:
                return (a, b) -> a * b;
            case DIVISION_OPERATOR:
                return (a, b) -> {
                    if (b == 0) throw new ArithmeticException(DIVISION_BY_ZERO_MESSAGE);
                    return a / b;
                };
            default:
                throw new IllegalArgumentException(UNKNOWN_OPERATOR_MESSAGE + operator);
        }
    }
} 
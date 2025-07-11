package lt.esdc.task2.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Pattern;

public class ArithmeticParser {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+(\\.\\d+)?");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("[+\\-*/]");

    private static final String UNEXPECTED_END_MESSAGE = "Unexpected end of expression";
    private static final String INVALID_TOKEN_MESSAGE = "Invalid token: ";
    private static final String WHITESPACE_DELIMITER = "\\s+";

    public Expression parse(String expression) {
        Deque<String> tokens = new ArrayDeque<>();
        for (String token : expression.split(WHITESPACE_DELIMITER)) {
            tokens.addLast(token);
        }
        return parseExpression(tokens);
    }

    private Expression parseExpression(Deque<String> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException(UNEXPECTED_END_MESSAGE);
        }

        String token = tokens.removeFirst();
        if (NUMBER_PATTERN.matcher(token).matches()) {
            return new NumberExpression(Double.parseDouble(token));
        }

        if (OPERATOR_PATTERN.matcher(token).matches()) {
            Expression left = parseExpression(tokens);
            Expression right = parseExpression(tokens);
            return new BinaryExpression(left, right, ArithmeticOperator.fromSymbol(token).getOperation());
        }

        throw new IllegalArgumentException(INVALID_TOKEN_MESSAGE + token);
    }
} 
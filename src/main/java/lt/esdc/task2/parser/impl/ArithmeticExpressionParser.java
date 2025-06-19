package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.interpreter.ArithmeticExpression;
import lt.esdc.task2.parser.AbstractParser;
import lt.esdc.task2.util.Logger;

import java.util.regex.Pattern;

public class ArithmeticExpressionParser extends AbstractParser {
    private static final Pattern ARITHMETIC_PATTERN = Pattern.compile("\\([^()]*[+\\-*/][^()]*\\)");

    @Override
    public TextComponent parse(String text) {
        // Проверяем, является ли текст арифметическим выражением
        if (isArithmeticExpression(text)) {
            try {
                Logger.debug("Parsing arithmetic expression: " + text);
                return new ArithmeticExpression(text);
            } catch (Exception e) {
                Logger.warn("Failed to parse as arithmetic expression: " + text + ", error: " + e.getMessage());
                // Если не удалось распарсить как арифметическое выражение,
                // передаем дальше по цепочке
                return parseNext(text);
            }
        }
        return parseNext(text);
    }

    private boolean isArithmeticExpression(String text) {
        // Проверяем наличие скобок и операторов
        boolean hasBrackets = text.contains("(") && text.contains(")");
        boolean hasOperators = text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/");
        boolean matchesPattern = text.matches(".*\\([^()]*[+\\-*/][^()]*\\).*");
        
        Logger.debug("Checking if '" + text + "' is arithmetic expression:");
        Logger.debug("  Has brackets: " + hasBrackets);
        Logger.debug("  Has operators: " + hasOperators);
        Logger.debug("  Matches pattern: " + matchesPattern);
        
        return hasBrackets && hasOperators && matchesPattern;
    }
} 
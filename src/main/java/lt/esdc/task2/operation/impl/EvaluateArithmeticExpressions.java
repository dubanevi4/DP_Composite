package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;
import lt.esdc.task2.interpreter.ArithmeticExpression;
import lt.esdc.task2.operation.TextOperation;
import lt.esdc.task2.util.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EvaluateArithmeticExpressions implements TextOperation {
    
    @Override
    public TextComponent execute(TextComponent text) {
        Logger.info("Starting arithmetic expression evaluation");
        StringBuilder result = new StringBuilder();
        List<String> foundExpressions = new ArrayList<>();
        
        // Сначала попробуем найти выражения в тексте напрямую
        String fullText = text.toString();
        Logger.info("Full text to search: " + fullText);
        findArithmeticExpressionsInText(fullText, foundExpressions);
        
        // Если не нашли, попробуем через композитную структуру
        if (foundExpressions.isEmpty()) {
            Logger.info("No expressions found in text, trying composite structure");
            findArithmeticExpressionsInComposite(text, foundExpressions);
        }
        
        if (foundExpressions.isEmpty()) {
            result.append("No arithmetic expressions found in the text.\n");
            Logger.info("No arithmetic expressions found");
        } else {
            result.append("Found arithmetic expressions:\n");
            Logger.info("Found " + foundExpressions.size() + " arithmetic expressions");
            for (String expression : foundExpressions) {
                try {
                    ArithmeticExpression arithmeticExpr = new ArithmeticExpression(expression);
                    double calculatedResult = arithmeticExpr.getResult();
                    result.append(String.format("Expression: %s = %s\n", 
                        expression, arithmeticExpr.toString()));
                    Logger.info("Evaluated: " + expression + " = " + calculatedResult);
                } catch (Exception e) {
                    result.append(String.format("Expression: %s - Error: %s\n", 
                        expression, e.getMessage()));
                    Logger.error("Error evaluating expression: " + expression, e);
                }
            }
        }
        
        return new ArithmeticEvaluationResult(result.toString());
    }
    
    private void findArithmeticExpressionsInText(String text, List<String> expressions) {
        // Используем более сложное регулярное выражение для поиска арифметических выражений
        // с возможностью вложенных скобок
        Pattern pattern = Pattern.compile("\\([^()]*[+\\-*/][^()]*\\)");
        Matcher matcher = pattern.matcher(text);
        
        while (matcher.find()) {
            String expression = matcher.group();
            if (!expressions.contains(expression)) {
                expressions.add(expression);
                Logger.debug("Found arithmetic expression in text: " + expression);
            }
        }
        
        // Если не нашли простые выражения, попробуем найти более сложные
        if (expressions.isEmpty()) {
            // Ищем выражения вида (число+число*число*(число+число-число-число))/число
            Pattern complexPattern = Pattern.compile("\\([^)]*[+\\-*/][^)]*\\)");
            Matcher complexMatcher = complexPattern.matcher(text);
            
            while (complexMatcher.find()) {
                String expression = complexMatcher.group();
                if (!expressions.contains(expression)) {
                    expressions.add(expression);
                    Logger.debug("Found complex arithmetic expression in text: " + expression);
                }
            }
        }
        
        // Если все еще не нашли, попробуем найти любое выражение со скобками и операторами
        if (expressions.isEmpty()) {
            // Ищем любые скобки с операторами внутри
            Pattern anyPattern = Pattern.compile("\\([^)]*[+\\-*/][^)]*\\)");
            Matcher anyMatcher = anyPattern.matcher(text);
            
            while (anyMatcher.find()) {
                String expression = anyMatcher.group();
                if (!expressions.contains(expression)) {
                    expressions.add(expression);
                    Logger.debug("Found any arithmetic expression in text: " + expression);
                }
            }
        }
    }
    
    private void findArithmeticExpressionsInComposite(TextComponent component, List<String> expressions) {
        // Проверяем тип компонента перед вызовом getComponents()
        if (component.getComponentType() == TextComponentType.SYMBOL) {
            // Symbol не имеет дочерних компонентов, пропускаем
            return;
        }
        
        if (component.getComponentType() == TextComponentType.LEXEME) {
            String lexemeText = component.toString();
            // Проверяем, содержит ли лексема арифметическое выражение
            if (isArithmeticExpression(lexemeText)) {
                expressions.add(lexemeText);
            }
        }
        
        // Рекурсивно проверяем дочерние компоненты только если они есть
        try {
            List<TextComponent> children = component.getComponents();
            for (TextComponent child : children) {
                findArithmeticExpressionsInComposite(child, expressions);
            }
        } catch (UnsupportedOperationException e) {
            // Компонент не поддерживает getComponents(), пропускаем
            Logger.debug("Component does not support getComponents(): " + component.getComponentType());
        }
    }
    
    private boolean isArithmeticExpression(String text) {
        return text.contains("(") && text.contains(")") && 
               (text.contains("+") || text.contains("-") || text.contains("*") || text.contains("/")) &&
               text.matches(".*\\([^()]*[+\\-*/][^()]*\\).*");
    }
    
    // Внутренний класс для результата
    private static class ArithmeticEvaluationResult implements TextComponent {
        private final String result;
        
        public ArithmeticEvaluationResult(String result) {
            this.result = result;
        }
        
        @Override
        public void add(TextComponent component) {
            throw new UnsupportedOperationException("Method is not supported for ArithmeticEvaluationResult");
        }
        
        @Override
        public void remove(TextComponent component) {
            throw new UnsupportedOperationException("Method is not supported for ArithmeticEvaluationResult");
        }
        
        @Override
        public TextComponent getChild(int index) {
            throw new UnsupportedOperationException("Method is not supported for ArithmeticEvaluationResult");
        }
        
        @Override
        public TextComponentType getComponentType() {
            return TextComponentType.TEXT;
        }
        
        @Override
        public List<TextComponent> getComponents() {
            throw new UnsupportedOperationException("Method is not supported for ArithmeticEvaluationResult");
        }
        
        @Override
        public String toString() {
            return result;
        }
    }
} 
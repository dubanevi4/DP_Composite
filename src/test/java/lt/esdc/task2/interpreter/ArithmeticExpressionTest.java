package lt.esdc.task2.interpreter;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ArithmeticExpressionTest {

    @Test
    void shouldCalculateComplexExpression() {
        // Тестируем выражение из входного файла: (7+5*12*(2+5-2-71))/12
        ArithmeticExpression expression = new ArithmeticExpression("(7+5*12*(2+5-2-71))/12");
        
        // Проверяем результат
        double result = expression.getResult();
        assertThat(result).isNotEqualTo(0.0);
        
        // Проверяем, что результат выводится как строка
        String resultString = expression.toString();
        assertThat(resultString).isNotEqualTo("(7+5*12*(2+5-2-71))/12");
        assertThat(resultString).matches("-?\\d+(\\.\\d+)?");
        
        System.out.println("Expression: " + expression.getOriginalExpression());
        System.out.println("Result: " + result);
    }

    @Test
    void shouldCalculateSimpleExpressions() {
        // Простые выражения
        ArithmeticExpression expr1 = new ArithmeticExpression("(2+3)");
        assertThat(expr1.getResult()).isEqualTo(5.0);
        
        ArithmeticExpression expr2 = new ArithmeticExpression("(10-3)");
        assertThat(expr2.getResult()).isEqualTo(7.0);
        
        ArithmeticExpression expr3 = new ArithmeticExpression("(4*5)");
        assertThat(expr3.getResult()).isEqualTo(20.0);
        
        ArithmeticExpression expr4 = new ArithmeticExpression("(15/3)");
        assertThat(expr4.getResult()).isEqualTo(5.0);
    }

    @Test
    void shouldCalculateExpressionWithPrecedence() {
        // Проверяем приоритет операций
        ArithmeticExpression expr = new ArithmeticExpression("(2+3*4)");
        assertThat(expr.getResult()).isEqualTo(14.0); // 2 + (3 * 4) = 2 + 12 = 14
    }

    @Test
    void shouldReturnOriginalExpression() {
        String originalExpr = "(2+3)";
        ArithmeticExpression expr = new ArithmeticExpression(originalExpr);
        assertThat(expr.getOriginalExpression()).isEqualTo(originalExpr);
    }

    @Test
    void shouldHaveCorrectComponentType() {
        ArithmeticExpression expr = new ArithmeticExpression("(2+3)");
        assertThat(expr.getComponentType().name()).isEqualTo("LEXEME");
    }
} 
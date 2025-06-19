package lt.esdc.task2.interpreter;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InfixArithmeticParserTest {
    private final InfixArithmeticParser parser = new InfixArithmeticParser();

    @Test
    void shouldParseSimpleAddition() {
        Expression expression = parser.parse("(2+3)");
        assertThat(expression.interpret()).isEqualTo(5.0);
    }

    @Test
    void shouldParseSimpleSubtraction() {
        Expression expression = parser.parse("(5-3)");
        assertThat(expression.interpret()).isEqualTo(2.0);
    }

    @Test
    void shouldParseSimpleMultiplication() {
        Expression expression = parser.parse("(4*3)");
        assertThat(expression.interpret()).isEqualTo(12.0);
    }

    @Test
    void shouldParseSimpleDivision() {
        Expression expression = parser.parse("(6/2)");
        assertThat(expression.interpret()).isEqualTo(3.0);
    }

    @Test
    void shouldParseComplexExpression() {
        Expression expression = parser.parse("(2+3*4)");
        assertThat(expression.interpret()).isEqualTo(14.0); // 2 + (3 * 4) = 2 + 12 = 14
    }

    @Test
    void shouldHandleDecimalNumbers() {
        Expression expression = parser.parse("(2.5+3.5)");
        assertThat(expression.interpret()).isEqualTo(6.0);
    }

    @Test
    void shouldParseExpressionFromInputFile() {
        // Тестируем выражение из входного файла: (7+5*12*(2+5-2-71))/12
        Expression expression = parser.parse("(7+5*12*(2+5-2-71))/12");
        double result = expression.interpret();
        assertThat(result).isNotEqualTo(0.0);
        System.out.println("Expression result: " + result);
    }

    @Test
    void shouldParseExpressionWithoutSpaces() {
        Expression expression = parser.parse("(2+3*4)");
        assertThat(expression.interpret()).isEqualTo(14.0);
    }
} 
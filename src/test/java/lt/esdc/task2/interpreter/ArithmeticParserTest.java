package lt.esdc.task2.interpreter;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArithmeticParserTest {
    private final ArithmeticParser parser = new ArithmeticParser();

    @Test
    void shouldParseSimpleAddition() {
        Expression expression = parser.parse("+ 2 3");
        assertThat(expression.interpret()).isEqualTo(5.0);
    }

    @Test
    void shouldParseSimpleSubtraction() {
        Expression expression = parser.parse("- 5 3");
        assertThat(expression.interpret()).isEqualTo(2.0);
    }

    @Test
    void shouldParseSimpleMultiplication() {
        Expression expression = parser.parse("* 4 3");
        assertThat(expression.interpret()).isEqualTo(12.0);
    }

    @Test
    void shouldParseSimpleDivision() {
        Expression expression = parser.parse("/ 6 2");
        assertThat(expression.interpret()).isEqualTo(3.0);
    }

    @Test
    void shouldParseComplexExpression() {
        Expression expression = parser.parse("+ * 2 3 4");
        assertThat(expression.interpret()).isEqualTo(10.0);
    }

    @Test
    void shouldHandleDecimalNumbers() {
        Expression expression = parser.parse("+ 2.5 3.5");
        assertThat(expression.interpret()).isEqualTo(6.0);
    }

    @Test
    void shouldThrowExceptionForDivisionByZero() {
        assertThatThrownBy(() -> parser.parse("/ 5 0"))
            .isInstanceOf(ArithmeticException.class)
            .hasMessage("Division by zero");
    }

    @Test
    void shouldThrowExceptionForInvalidExpression() {
        assertThatThrownBy(() -> parser.parse("invalid"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid token: invalid");
    }
} 
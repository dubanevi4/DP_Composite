package lt.esdc.task2.parser.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IndividualParserTest {
    
    @Test
    void paragraphParserShouldSplitIntoSentences() {
        // given
        ParagraphParser parser = new ParagraphParser();
        String text = "First sentence. Second sentence! Third sentence?";

        // when
        TextComponent result = parser.parse(text);

        // then
        assertThat(result.getComponentType()).isEqualTo(TextComponentType.PARAGRAPH);
        assertThat(result.getComponents()).hasSize(3);
    }

    @Test
    void sentenceParserShouldSplitIntoLexemes() {
        // given
        SentenceParser parser = new SentenceParser();
        String text = "This is a test sentence";

        // when
        TextComponent result = parser.parse(text);

        // then
        assertThat(result.getComponentType()).isEqualTo(TextComponentType.SENTENCE);
        assertThat(result.getComponents()).hasSize(5); // "This", "is", "a", "test", "sentence"
    }

    @Test
    void lexemeParserShouldSplitIntoSymbols() {
        // given
        LexemeParser parser = new LexemeParser();
        String text = "test";

        // when
        TextComponent result = parser.parse(text);

        // then
        assertThat(result.getComponentType()).isEqualTo(TextComponentType.LEXEME);
        assertThat(result.getComponents()).hasSize(4); // 't', 'e', 's', 't'
    }

    @Test
    void symbolParserShouldCreateSymbol() {
        // given
        SymbolParser parser = new SymbolParser();
        String text = "a";

        // when
        TextComponent result = parser.parse(text);

        // then
        assertThat(result.getComponentType()).isEqualTo(TextComponentType.SYMBOL);
        assertThat(result.toString()).isEqualTo("a");
    }

    @Test
    void shouldHandleSpacesCorrectly() {
        // given
        SentenceParser parser = new SentenceParser();
        String text = "Hello, world! This is a test.";

        // when
        TextComponent result = parser.parse(text);

        // then
        String reconstructed = result.toString();
        assertThat(reconstructed).isEqualTo("Hello, world! This is a test.");
        assertThat(reconstructed).doesNotContain("  "); // No double spaces
        assertThat(reconstructed).doesNotContain(" ,"); // No space before comma
        assertThat(reconstructed).doesNotContain(" ."); // No space before period
    }
} 
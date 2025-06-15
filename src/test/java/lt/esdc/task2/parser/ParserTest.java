package lt.esdc.task2.parser;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;
import lt.esdc.task2.reader.DataReader;
import lt.esdc.task2.exception.ProjectException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {
    private AbstractParser parser;
    private DataReader reader;

    @BeforeEach
    void setUp() {
        parser = ParserChainBuilder.buildParserChain();
        reader = new DataReader();
    }

    @Test
    void shouldParseTextIntoComponents() throws ProjectException {
        // given
        String text = reader.readText("input.txt");

        // when
        TextComponent result = parser.parse(text);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getComponentType()).isEqualTo(TextComponentType.TEXT);
        assertThat(result.getComponents()).isNotEmpty();
        
        // Check paragraphs
        TextComponent firstParagraph = result.getChild(0);
        assertThat(firstParagraph.getComponentType()).isEqualTo(TextComponentType.PARAGRAPH);
        assertThat(firstParagraph.getComponents()).isNotEmpty();
        
        // Check sentences
        TextComponent firstSentence = firstParagraph.getChild(0);
        assertThat(firstSentence.getComponentType()).isEqualTo(TextComponentType.SENTENCE);
        assertThat(firstSentence.getComponents()).isNotEmpty();
        
        // Check lexemes
        TextComponent firstLexeme = firstSentence.getChild(0);
        assertThat(firstLexeme.getComponentType()).isEqualTo(TextComponentType.LEXEME);
        assertThat(firstLexeme.getComponents()).isNotEmpty();
        
        // Check symbols
        TextComponent firstSymbol = firstLexeme.getChild(0);
        assertThat(firstSymbol.getComponentType()).isEqualTo(TextComponentType.SYMBOL);
    }

    @Test
    void shouldPreserveTextContent() throws ProjectException {
        // given
        String text = reader.readText("input.txt");

        // when
        TextComponent result = parser.parse(text);

        // then
        String reconstructedText = result.toString();
        assertThat(reconstructedText).contains("This is the first paragraph");
        assertThat(reconstructedText).contains("This is the second paragraph");
        assertThat(reconstructedText).contains("This is the third paragraph");
    }

    @Test
    void shouldHandleEmptyText() {
        // when
        TextComponent result = parser.parse("");

        // then
        assertThat(result).isNotNull();
        assertThat(result.getComponents()).isEmpty();
    }
} 
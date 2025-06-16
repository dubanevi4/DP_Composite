package lt.esdc.task2.operation;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.*;
import lt.esdc.task2.operation.impl.*;
import lt.esdc.task2.parser.AbstractParser;
import lt.esdc.task2.parser.ParserChainBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TextOperationsTest {
    private TextComponent text;
    private AbstractParser parser;

    @BeforeEach
    void setUp() {
        parser = ParserChainBuilder.buildParserChain();
        text = createTestText();
    }

    private TextComponent createTestText() {
        String testText = "    First paragraph. It has two sentences! This is a test.\n" +
                         "    Second paragraph. It also has two sentences. This is another test.\n" +
                         "    Third paragraph. It has three sentences! This is the longest word in text. This is a test.";
        return parser.parse(testText);
    }

    @Test
    void shouldSortParagraphsBySentenceCount() {
        // given
        TextOperation operation = new SortParagraphsBySentenceCount();

        // when
        TextComponent result = operation.execute(text);

        // then
        assertThat(result.getComponents()).hasSize(3);
        assertThat(result.getChild(0).getComponents()).hasSize(2); // Second paragraph
        assertThat(result.getChild(1).getComponents()).hasSize(2); // First paragraph
        assertThat(result.getChild(2).getComponents()).hasSize(3); // Third paragraph
    }

    @Test
    void shouldFindSentencesWithLongestWord() {
        // given
        TextOperation operation = new FindSentencesWithLongestWord();

        // when
        TextComponent result = operation.execute(text);

        // then
        assertThat(result.toString()).contains("longest word");
        assertThat(result.getComponents()).hasSize(1);
    }

    @Test
    void shouldRemoveShortSentences() {
        // given
        TextOperation operation = new RemoveShortSentences(4);

        // when
        TextComponent result = operation.execute(text);

        // then
        assertThat(result.toString()).doesNotContain("First paragraph");
        assertThat(result.toString()).doesNotContain("Second paragraph");
        assertThat(result.toString()).contains("This is the longest word in text");
    }

    @Test
    void shouldCountDuplicateWords() {
        // given
        TextOperation operation = new CountDuplicateWords();

        // when
        TextComponent result = operation.execute(text);

        // then
        String resultStr = result.toString();
        assertThat(resultStr).contains("test: 3");
        assertThat(resultStr).contains("this: 4");
        assertThat(resultStr).contains("is: 4");
    }

    @Test
    void shouldCountVowelsAndConsonants() {
        // given
        TextOperation operation = new CountVowelsAndConsonants();

        // when
        TextComponent result = operation.execute(text);

        // then
        String resultStr = result.toString();
        assertThat(resultStr).contains("Vowels:");
        assertThat(resultStr).contains("Consonants:");
        assertThat(resultStr).contains("First paragraph");
        assertThat(resultStr).contains("Second paragraph");
        assertThat(resultStr).contains("Third paragraph");
    }
} 
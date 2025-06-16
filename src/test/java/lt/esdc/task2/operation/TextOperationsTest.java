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
        String testText = new StringBuilder("This is the first paragraph. It has two sentences! " +
                "This is a test paragraph.\n" +
                "Here is the second paragraph. " +
                "It contains three sentences. This is another test paragraph. " +
                "The last sentence has more words than the others.\n" +
                "This is the third paragraph. It has the longestlongestlongest word.").toString();
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
        assertThat(result.getChild(1).getComponents()).hasSize(3); // First paragraph
        assertThat(result.getChild(2).getComponents()).hasSize(4); // Third paragraph
    }

    @Test
    void shouldFindSentencesWithLongestWord() {
        // given
        TextOperation operation = new FindSentencesWithLongestWord();

        // when
        TextComponent result = operation.execute(text);

        // then
        assertThat(result.toString()).contains("longestlongestlongest word");
        assertThat(result.getComponents()).hasSize(1);
    }

    @Test
    void shouldRemoveShortSentences() {
        // given
        TextOperation operation = new RemoveShortSentences(9);

        // when
        TextComponent result = operation.execute(text);

        // then
        assertThat(result.toString()).contains("The last sentence has more words than the others.");
    }

    @Test
    void shouldCountDuplicateWords() {
        // given
        TextOperation operation = new CountDuplicateWords();

        // when
        TextComponent result = operation.execute(text);

        // then
        String resultStr = result.toString();
        assertThat(resultStr).contains("test: 2");
        assertThat(resultStr).contains("this: 4");
        assertThat(resultStr).contains("is: 5");
    }

    @Test
    void shouldCountVowelsAndConsonants() {
        // given
        TextOperation operation = new CountVowelsAndConsonants();

        // when
        TextComponent result = operation.execute(text);

        // then
        String resultStr = result.toString();
        assertThat(resultStr).contains("Sentence: This is the first paragraph.\n" +
                "Vowels: 7, Consonants: 16");
        assertThat(resultStr).contains("Sentence: Here is the second paragraph.\n" +
                "Vowels: 9, Consonants: 15");
        assertThat(resultStr).contains("Sentence: The last sentence has more words than the others.\n" +
                "Vowels: 13, Consonants: 27");
    }
} 
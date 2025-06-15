package lt.esdc.task2.reader;

import lt.esdc.task2.exception.ProjectException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DataReaderTest {
    private final DataReader reader = new DataReader();

    @Test
    void shouldReadTextFromFile() throws ProjectException {
        // when
        String text = reader.readText("input.txt");

        // then
        assertThat(text).isNotNull();
        assertThat(text).isNotEmpty();
        assertThat(text).contains("This is the first paragraph");
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        // when/then
        assertThatThrownBy(() -> reader.readText("nonexistent.txt"))
                .isInstanceOf(ProjectException.class)
                .hasMessageContaining("Problem with the path to file");
    }
} 
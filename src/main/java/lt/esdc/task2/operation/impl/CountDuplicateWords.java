package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;
import lt.esdc.task2.operation.TextOperation;
import lt.esdc.task2.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountDuplicateWords implements TextOperation {
    @Override
    public TextComponent execute(TextComponent text) {
        Logger.info("Starting duplicate words count operation");
        Map<String, Integer> wordCount = new HashMap<>();
        
        // Count words in all components
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                for (TextComponent lexeme : sentence.getComponents()) {
                    if (lexeme.getComponentType() == TextComponentType.LEXEME) {
                        String word = lexeme.toString().toLowerCase();
                        // Remove punctuation from the word
                        word = word.replaceAll("[.,!?]", "");
                        wordCount.merge(word, 1, Integer::sum);
                        Logger.debug("Processed word: " + word);
                    }
                }
            }
        }
        
        // Filter out words that appear only once
        int initialSize = wordCount.size();
        wordCount.entrySet().removeIf(entry -> entry.getValue() <= 1);
        int finalSize = wordCount.size();
        
        Logger.info("Found " + (initialSize - finalSize) + " unique words and " + finalSize + " duplicate words");
        
        return new WordCountResult(wordCount);
    }

    private static class WordCountResult implements TextComponent {
        private final Map<String, Integer> wordCount;

        public WordCountResult(Map<String, Integer> wordCount) {
            this.wordCount = wordCount;
            Logger.debug("Created WordCountResult with " + wordCount.size() + " duplicate words");
        }

        @Override
        public TextComponentType getComponentType() {
            return TextComponentType.TEXT;
        }

        @Override
        public List<TextComponent> getComponents() {
            return List.of();
        }

        @Override
        public void add(TextComponent component) {
            Logger.warn("Attempted to add component to WordCountResult");
            throw new UnsupportedOperationException("Cannot add components to WordCountResult");
        }

        @Override
        public void remove(TextComponent component) {
            Logger.warn("Attempted to remove component from WordCountResult");
            throw new UnsupportedOperationException("Cannot remove components from WordCountResult");
        }

        @Override
        public TextComponent getChild(int index) {
            Logger.warn("Attempted to get child from WordCountResult");
            throw new UnsupportedOperationException("WordCountResult does not support child components");
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("Duplicate words count:\n");
            wordCount.forEach((word, count) -> result.append(word).append(": ").append(count).append("\n"));
            return result.toString();
        }
    }
} 
package lt.esdc.task2.operation.impl;

import lt.esdc.task2.composite.TextComponent;
import lt.esdc.task2.composite.impl.TextComponentType;
import lt.esdc.task2.operation.TextOperation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountVowelsAndConsonants implements TextOperation {
    private static final Set<Character> VOWELS = new HashSet<>();
    
    static {
        VOWELS.add('a');
        VOWELS.add('e');
        VOWELS.add('i');
        VOWELS.add('o');
        VOWELS.add('u');
        VOWELS.add('y');
    }

    @Override
    public TextComponent execute(TextComponent text) {
        StringBuilder result = new StringBuilder();
        
        for (TextComponent paragraph : text.getComponents()) {
            for (TextComponent sentence : paragraph.getComponents()) {
                int[] counts = countLetters(sentence);
                result.append(String.format("Sentence: %s\n", sentence.toString().trim()));
                result.append(String.format("Vowels: %d, Consonants: %d\n\n", counts[0], counts[1]));
            }
        }
        
        return new LetterCountResult(result.toString());
    }

    private int[] countLetters(TextComponent sentence) {
        int vowels = 0;
        int consonants = 0;
        
        for (TextComponent lexeme : sentence.getComponents()) {
            String word = lexeme.toString().toLowerCase();
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    if (VOWELS.contains(c)) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }
        }
        
        return new int[]{vowels, consonants};
    }

    private static class LetterCountResult implements TextComponent {
        private final String result;

        public LetterCountResult(String result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return result;
        }

        // Implement other TextComponent methods with default behavior
        @Override
        public void add(TextComponent component) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove(TextComponent component) {
            throw new UnsupportedOperationException();
        }

        @Override
        public TextComponent getChild(int index) {
            throw new UnsupportedOperationException();
        }

        @Override
        public TextComponentType getComponentType() {
            return TextComponentType.TEXT;
        }

        @Override
        public List<TextComponent> getComponents() {
            throw new UnsupportedOperationException();
        }
    }
} 
package lt.esdc.task2.parser;

import lt.esdc.task2.parser.impl.*;

public class ParserChainBuilder {
    public static AbstractParser buildParserChain() {
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        ArithmeticExpressionParser arithmeticExpressionParser = new ArithmeticExpressionParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();

        textParser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(arithmeticExpressionParser);
        arithmeticExpressionParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(symbolParser);

        return textParser;
    }
} 
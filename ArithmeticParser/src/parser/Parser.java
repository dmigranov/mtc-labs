package parser;

import lexer.Lexeme;
import lexer.LexemeType;
import lexer.Lexer;
import lexer.LexerException;

import java.io.Reader;

public class Parser {
    private Lexer lexer;
    private Lexeme current;

    public Parser(Reader reader) throws LexerException
    {
        lexer = new Lexer(reader);
    }

    public int calculate() throws ParserException
    {
        current = lexer.getLexeme();

        int temp = parseExpression();

        if(current.getType() != LexemeType.EOF)
            throw new ParserException();
        return temp;
    }

    private int parseExpression() throws ParserException
    {
        int temp = parseTerm();

        while (current.getType() == LexemeType.PLUS || current.getType() == LexemeType.MINUS)
        {
            if (current.getType() == LexemeType.PLUS)
            {
                current = lexer.getLexeme();
                temp += parseTerm();
            }
            else
            {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }

        return temp;
    }

    private int parseTerm() throws ParserException
    {
        int temp = parseFactor();

        while (current.getType() == LexemeType.ASTERISK || current.getType() == LexemeType.SLASH)
        {
            if (current.getType() == LexemeType.ASTERISK)
            {
                current = lexer.getLexeme();
                temp *= parseFactor();
            }
            else
            {
                current = lexer.getLexeme();
                temp /= parseFactor();
            }
        }

        return temp;
    }

    private int parseFactor() throws ParserException
    {
        int temp = parsePower();

        if (current.getType() == LexemeType.POWER)
        {
            current = lexer.getLexeme();
            temp = (int)Math.pow(temp, parseFactor());
        }

        return temp;
    }

    private int parsePower() throws ParserException
    {
        if (current.getType() == LexemeType.MINUS)
        {
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws ParserException
    {
        if(current.getType() == LexemeType.NUMBER)
        {
            String text = current.getText();
            current = lexer.getLexeme();
            return Integer.parseInt(text);
        }
        if(current.getType() == LexemeType.LBRACE)
        {
            current = lexer.getLexeme();
            int temp = parseExpression();
            if (current.getType() != LexemeType.RBRACE)
                throw new ParserException();
            current = lexer.getLexeme();
            return temp;
        }

        throw new ParserException();
    }
}

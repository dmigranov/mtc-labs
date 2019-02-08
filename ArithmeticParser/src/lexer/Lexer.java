package lexer;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private char current;
    private Reader reader;

    public Lexer(Reader reader) throws LexerException
    {
        this.reader = reader;

        try {
            int temp;
            if((temp = reader.read()) == -1)
                throw new LexerException();
            current = (char)temp;
        } catch (IOException e) {
            throw new LexerException();
        }

    }

    public Lexeme getLexeme() throws LexerException
    {
        try {
            int temp = current;
            while (Character.isSpaceChar(temp)) {
                temp = reader.read();
            }
            if(temp == -1)
                throw new LexerException();
            current = (char)temp;


            if (Character.isDigit(current))
            {

            } else switch (current) {
                case ('+'):
                case ('-'):
                case ('*'):
                case ('/'):
                case ('('):
                case (')'):
                case ('^'):
                    return new Lexeme();    //
                break;
                default:
                    throw new LexerException();
            }
        }
        catch (IOException e)
        {
            throw new LexerException();
        }
    }

    private void readNextChar()
    {

    }

    private boolean isMathSymbol()
    {

    }
}

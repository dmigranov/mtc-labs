package lexer;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    //private char current;
    private int current;
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
            //int temp = current;
            while (Character.isSpaceChar(current)) {
                current = reader.read();
            }
            if(current == -1)
                return new Lexeme(LexemeType.EOF);



            if (Character.isDigit(current))
            {
                StringBuilder builder = new StringBuilder();
                while(Character.isDigit(current)) {
                    builder.append((char)current);
                    current = reader.read();
                }

                return new Lexeme(builder.toString());
            }
            else
            {
                int mathSymbol = current;

                current = reader.read();      //это хорошая идея? не надо проверять на -1?

                switch (mathSymbol)
                {
                //TODO: может, поменять как-то?
                    case ('+'):
                        return new Lexeme(LexemeType.PLUS);
                    case ('-'):
                        return new Lexeme(LexemeType.MINUS);
                    case ('*'):
                        return new Lexeme(LexemeType.ASTERISK);
                    case ('/'):
                        return new Lexeme(LexemeType.SLASH);
                    case ('('):
                        return new Lexeme(LexemeType.LBRACE);
                    case (')'):
                        return new Lexeme(LexemeType.RBRACE);
                    case ('^'):
                        return new Lexeme(LexemeType.POWER);    //
                    default:
                        throw new LexerException();
                }
            }
        }
        catch (IOException e)
        {
            throw new LexerException();
        }
    }

}

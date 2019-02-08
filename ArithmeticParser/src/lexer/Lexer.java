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
                return new Lexeme(LexemeType.EOF);
            current = (char)temp;


            if (Character.isDigit(current))
            {
                StringBuilder builder = new StringBuilder();
                while(Character.isDigit(temp)) {
                    builder.append((char)temp);
                    temp = reader.read();
                }

                current = (char)temp;
                return new Lexeme(builder.toString());
            }
            else
            {
                Lexeme l;
                switch (current)
                {
                //TODO: может, поменять как-то?
                    case ('+'):
                        l =  new Lexeme(LexemeType.PLUS);
                    case ('-'):
                        l =  new Lexeme(LexemeType.MINUS);
                    case ('*'):
                        l =  new Lexeme(LexemeType.ASTERISK);
                    case ('/'):
                        l =  new Lexeme(LexemeType.SLASH);
                    case ('('):
                        l =  new Lexeme(LexemeType.LBRACE);
                    case (')'):
                        l =  new Lexeme(LexemeType.RBRACE);
                    case ('^'):
                        l =  new Lexeme(LexemeType.POWER);

                    default:
                        throw new LexerException();
                }
                //прочитать новый current!
                return l;
            }
        }
        catch (IOException e)
        {
            throw new LexerException();
        }
    }

}

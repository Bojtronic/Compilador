package analisis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexico {
    private String input;
    private int position;
    private Token currentToken;

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-zA-Z_]([a-zA-Z0-9_]|_[a-zA-Z0-9_])*");

    public Lexico() {
        this.input = "";
        this.position = 0;
        this.currentToken = null;
    }

    public void initLexer(String input) {
        this.input = input;
        this.position = 0;
        this.currentToken = null;
    }
    
    
    public Token getNextToken() {
    if (position >= input.length()) {
        return new Token(Token.TokenType.EOF, "");
    }

    String restOfInput = input.substring(position);

    Matcher numberMatcher = NUMBER_PATTERN.matcher(restOfInput);
    if (numberMatcher.lookingAt()) {
        String numberStr = numberMatcher.group();
        position += numberStr.length();
        return new Token(Token.TokenType.NUMBER, numberStr);
    }

    Matcher identifierMatcher = IDENTIFIER_PATTERN.matcher(restOfInput);
    if (identifierMatcher.lookingAt()) {
        String identifierStr = identifierMatcher.group();
        position += identifierStr.length();
        switch (identifierStr) {
            case "begin":
                return new Token(Token.TokenType.BEGIN, identifierStr);
            case "end":
                return new Token(Token.TokenType.END, identifierStr);
            case "do":
                return new Token(Token.TokenType.DO, identifierStr);
            case "od":
                return new Token(Token.TokenType.OD, identifierStr);
            case "if":
                return new Token(Token.TokenType.IF, identifierStr);
            case "fi":
                return new Token(Token.TokenType.FI, identifierStr);
            case "then":
                return new Token(Token.TokenType.THEN, identifierStr);
            case "else":
                return new Token(Token.TokenType.ELSE, identifierStr);
            case "while":
                return new Token(Token.TokenType.WHILE, identifierStr);
            case "for":
                return new Token(Token.TokenType.FOR, identifierStr);
            case "to":
                return new Token(Token.TokenType.TO, identifierStr);
            case "by":
                return new Token(Token.TokenType.BY, identifierStr);
            case "not":
                return new Token(Token.TokenType.NOT, identifierStr);
            case "and":
                return new Token(Token.TokenType.AND, identifierStr);
            case "or":
                return new Token(Token.TokenType.OR, identifierStr);
            default:
                return new Token(Token.TokenType.IDENTIFIER, identifierStr);
        }
    }

    char currentChar = input.charAt(position);

    switch (currentChar) {
        case '+':
            position++;
            return new Token(Token.TokenType.PLUS, "+");
        case '-':
            position++;
            return new Token(Token.TokenType.MINUS, "-");
        case '*':
            position++;
            return new Token(Token.TokenType.MULTIPLY, "*");
        case '/':
            position++;
            return new Token(Token.TokenType.DIVIDE, "/");
        case '(':
            position++;
            return new Token(Token.TokenType.LPAREN, "(");
        case ')':
            position++;
            return new Token(Token.TokenType.RPAREN, ")");
        default:
            position++;
            return new Token(Token.TokenType.INVALID, Character.toString(currentChar));
    }
}


    

    public String lex(String input) {
        initLexer(input);
        StringBuilder outputBuilder = new StringBuilder();
        Token token;
        do {
            token = getNextToken();
            outputBuilder.append(token.toString()).append('\n');
        } while (token.getType() != Token.TokenType.EOF);
        return outputBuilder.toString();
    }
}

package analisis;

public class Token {
  
  /*
    public enum TokenType {
        BEGIN, END, DO, OD, IF, FI, THEN, ELSE, WHILE, FOR, TO, BY, PLUS, MINUS, MULTIPLY,
        DIVIDE, ASSIGN, LT, LE, GT, GE, EQ, NE, AND, OR, NOT, SEMICOLON, COMMA, LPAREN,
        RPAREN, NUMBER, IDENTIFIER, EOF
    }
*/
    
    public enum TokenType {
    EOF,
    NUMBER,
    IDENTIFIER,
    BEGIN,
    END,
    DO,
    OD,
    IF,
    FI,
    THEN,
    ELSE,
    WHILE,
    FOR,
    TO,
    BY,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    LPAREN,
    RPAREN,
    NOT,
    AND,
    OR,
    INVALID
} 

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        if (value != null) {
            return String.format("%s(%s)", type, value);
        } else {
            return type.toString();
        }
    }
}
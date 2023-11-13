package org.example.token;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для описания токена выражения
 */
public class Token {
    /**
     * Значение токена
     */
    private final String token;
    /**
     * Тип токена
     */
    private TokenType type;

    public Token(String token) {
        this.token = token;

        if (Arrays.asList(TokenType.FUNCTION.availableList).contains(token))
            type = TokenType.FUNCTION;
        else if (Arrays.asList(TokenType.OPERATOR.availableList).contains(token))
            type = TokenType.OPERATOR;
        else if (Arrays.asList(TokenType.OPEN_BRACKET.availableList).contains(token))
            type = TokenType.OPEN_BRACKET;
        else if (Arrays.asList(TokenType.CLOSE_BRACKET.availableList).contains(token))
            type = TokenType.CLOSE_BRACKET;
        else if (Arrays.asList(TokenType.CONSTANT.availableList).contains(token))
            type = TokenType.CONSTANT;
        else {
            try {
                Double.parseDouble(token);
                type = TokenType.NUMBER;
            } catch (Exception ignored) {
                String regex = "\\w+\\d*";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(token);
                if (matcher.find() && matcher.group().equals(token))
                    type = TokenType.VARIABLE;
                else
                    type = TokenType.NO_TYPE;
            }
        }
    }

    /**
     * Получить приоритет токена
     * @return
     */
    public int getPrecedence() {
        if (token.equals("+") || token.equals("-") || token.equals("!"))
            return 1;
        if (token.equals("*") || token.equals("/"))
            return 2;
        if (token.equals("^"))
            return 3;
        return -1;
    }

    public String getToken() {
        return token;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token) && type == token1.type;
    }
}

package org.example.token;

/**
 * Перечисление со всеми возможными типами токенов
 */
public enum TokenType {
    FUNCTION(new String[]{"sin", "cos", "tg", "ctg", "ln", "lg10", "sqrt", "sign"}),
    OPERATOR(new String[]{"+", "-", "*", "/", "^"}),
    NUMBER,
    VARIABLE,
    CONSTANT(new String[]{"e", "pi"}),
    OPEN_BRACKET(new String[]{"("}),
    CLOSE_BRACKET(new String[]{")"}),
    NO_TYPE;

    /**
     * Список обрабатываемых символов если такой имеется
     */
    public final String[] availableList;

    TokenType(String[] availableList) {
        this.availableList = availableList;
    }

    TokenType() {
        availableList = null;
    }
}

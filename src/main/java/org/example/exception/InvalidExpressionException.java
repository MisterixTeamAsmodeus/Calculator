package org.example.exception;

/**
 * Ошибка о некоректной записи выражения
 */
public class InvalidExpressionException extends Exception {
    public InvalidExpressionException(String message) {
        super(message);
    }
}

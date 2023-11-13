package org.example.exception;

/**
 * Ошибка о не обрабатываемом токене
 */
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message) {
        super(message);
    }
}

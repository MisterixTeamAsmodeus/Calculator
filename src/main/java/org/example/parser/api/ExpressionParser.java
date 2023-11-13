package org.example.parser.api;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.token.Token;

import java.util.Stack;

/**
 * Шаблон парсера для работы с выражениями
 */
public abstract class ExpressionParser {
    /**
     * Функциональный интерфейс для генерации regex для проверки валидности выражения
     */
    protected RegexCompiler compiler;

    public ExpressionParser(RegexCompiler compiler) {
        this.compiler = compiler;
    }

    /**
     * Функция для парсинга выражения и преобразования её в коллекцию токенов
     * @param expression Выражение для разбора
     * @return Возвращает коллекцию обработанных токенов
     * @throws InvalidTokenException Вызывается в случае обнаружения не обрабатываемых символов
     * @throws InvalidExpressionException Вызывается в случае невалидного выражения для парсинга
     */
    public abstract Stack<Token> parse(String expression) throws InvalidTokenException, InvalidExpressionException;
}

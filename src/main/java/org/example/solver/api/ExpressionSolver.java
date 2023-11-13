package org.example.solver.api;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.token.Token;

import java.util.Stack;

/**
 * Интерфейс для решения выражения заданного вида
 */
@FunctionalInterface
public interface ExpressionSolver {
    /**
     * Решение выражения
     * @param tokens Выражение которое необходимо решить
     * @return Возращает решенное выражение
     * @throws InvalidTokenException Вызывается в случае обнаружения не обрабатываемых символов
     * @throws InvalidExpressionException Вызывается в случае невалидного выражения для парсинга
     */
    double solve(Stack<Token> tokens) throws InvalidExpressionException, InvalidTokenException;
}

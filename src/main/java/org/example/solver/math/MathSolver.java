package org.example.solver.math;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.solver.api.ExpressionSolver;
import org.example.token.Token;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class MathSolver implements ExpressionSolver {
    @Override
    public double solve(@NotNull Stack<Token> tokens) throws InvalidTokenException, InvalidExpressionException {
        Token token = tokens.pop();
        switch (token.getType()) {
            case FUNCTION: {
                UnaryOperator<Double> function = getFunction(token.getToken());
                double value = solve(tokens);
                return function.apply(value);
            }
            case OPERATOR: {
                BinaryOperator<Double> operator = getBinaryOperation(token.getToken());
                double right = solve(tokens);
                double left = solve(tokens);
                return operator.apply(left, right);
            }

            case NUMBER:
                return Double.parseDouble(token.getToken());

            case CONSTANT: {
                return getConstant(token.getToken());
            }
            default:
                throw new InvalidExpressionException("Недопустимый символ: " + token.getToken());
        }
    }

    /**
     * Получение операции по её названию в выражении
     *
     * @param operation Название операции
     * @return Возвращает бинарную операцию
     * @throws InvalidTokenException Вызывается в случае обнаружения не обрабатываемых символов
     */
    @NotNull
    private BinaryOperator<Double> getBinaryOperation(@NotNull String operation) throws InvalidTokenException {
        switch (operation) {
            case "+":
                return Double::sum;
            case "-":
                return (left, right) -> left - right;
            case "*":
                return (left, right) -> left * right;
            case "/":
                return (left, right) -> left / right;
            case "^":
                return Math::pow;
            default:
                throw new InvalidTokenException("Неопознанный оператор: " + operation);
        }
    }

    /**
     * Получение функции по её названию
     *
     * @param function Название функции
     * @return Возращает унарную функцию
     * @throws InvalidTokenException Вызывается в случае обнаружения не обрабатываемых символов
     */
    @NotNull
    private UnaryOperator<Double> getFunction(@NotNull String function) throws InvalidTokenException {
        switch (function) {
            case "sin":
                return Math::sin;
            case "cos":
                return Math::cos;
            case "tg":
                return Math::tan;
            case "ln":
                return Math::log;
            case "lg10":
                return Math::log10;
            case "sqrt":
                return Math::sqrt;
            case "sign":
                return Math::signum;
            default:
                throw new InvalidTokenException("Неопознанная функция: " + function);
        }
    }

    /**
     * Получение константы по её названию
     *
     * @param constant Название константы
     * @return Значение константы
     * @throws InvalidTokenException Вызывается в случае обнаружения не обрабатываемых символов
     */
    private double getConstant(@NotNull String constant) throws InvalidTokenException {
        switch (constant) {
            case "pi":
                return Math.PI;
            case "e":
                return Math.E;
            default:
                throw new InvalidTokenException("Неопознанная константа: " + constant);
        }
    }
}

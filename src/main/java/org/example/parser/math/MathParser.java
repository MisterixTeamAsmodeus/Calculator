package org.example.parser.math;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.parser.api.ExpressionParser;
import org.example.parser.api.RegexCompiler;
import org.example.token.Token;
import org.example.token.TokenType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathParser extends ExpressionParser {

    public MathParser(RegexCompiler compiler) {
        super(compiler);
    }

    /**
     * @param expression Выражение для разбора
     * @return
     * @throws InvalidTokenException
     * @throws InvalidExpressionException
     */
    @Override
    public Stack<Token> parse(String expression) throws InvalidTokenException, InvalidExpressionException {
        Stack<Token> stackOperations = new Stack<>();
        Stack<Token> stackRPN = new Stack<>();
        Map<String, Double> var = new HashMap<>();

        StringTokenizer stringTokenizer = new StringTokenizer(validate(expression), compiler.getDelim(), true);

        while (stringTokenizer.hasMoreTokens()) {
            Token token = new Token(stringTokenizer.nextToken());

            switch (token.getType()) {
                case FUNCTION:
                case OPEN_BRACKET: {
                    stackOperations.push(token);
                    break;
                }

                case CONSTANT:
                case NUMBER: {
                    stackRPN.push(token);
                    break;
                }

                case OPERATOR: {
                    while (!stackOperations.empty() &&
                            stackOperations.lastElement().getType() == TokenType.OPERATOR &&
                            token.getPrecedence() <= stackOperations.lastElement().getPrecedence()) {
                        stackRPN.push(stackOperations.pop());
                    }
                    stackOperations.push(token);
                    break;
                }

                case VARIABLE: {
                    if (!var.containsKey(token.getToken())) {
                        System.out.print("Enter " + token.getToken() + " : ");
                        Scanner scanner = new Scanner(System.in);
                        var.put(token.getToken(), scanner.nextDouble());
                    }
                    stackRPN.push(new Token(var.get(token.getToken()).toString()));
                    break;
                }

                case CLOSE_BRACKET: {
                    while (!stackOperations.empty() && stackOperations.lastElement().getType() != TokenType.OPEN_BRACKET) {
                        stackRPN.push(stackOperations.pop());
                    }
                    stackOperations.pop();
                    if (!stackOperations.empty() && stackOperations.lastElement().getType() == TokenType.FUNCTION) {
                        stackRPN.push(stackOperations.pop());
                    }
                    break;
                }

                case NO_TYPE:
                    throw new InvalidTokenException("Неопознанный символ: " + token);
            }
        }

        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        return stackRPN;
    }

    /**
     * Преобразование выражения для начала разбора на токены с первоначальной проверкой на валидность
     * @param expression Выражение для преобразования
     * @return Возращает преобразованное выражение для проверки
     * @throws InvalidExpressionException Вызывается в случае невалидного выражения
     */
    @NotNull
    private String validate(String expression) throws InvalidExpressionException {
        expression = expression
                .replace(" ", "")
                .replace("(-", "(0-")
                .replace("(+", "(0+");
        if (expression.charAt(0) == '-' || expression.charAt(0) == '+') {
            expression = "0" + expression;
        }

        isCorrect(expression);

        return expression;
    }

    /**
     * Проверка выражения на правильность записи
     * @param expression Выражение для проверки
     * @throws InvalidExpressionException Вызывается в случае невалидного выражения
     */
    private void isCorrect(@NotNull String expression) throws InvalidExpressionException {
        int countOpen = 0;
        int countClose = 0;
        for (char c : expression.toCharArray()) {
            countOpen += c == '(' ? 1 : 0;
            countClose += c == ')' ? 1 : 0;
        }
        if (countOpen != countClose)
            throw new InvalidExpressionException("Количество открытых скобок не соответсвует количеству закрытых");

        Pattern pattern = Pattern.compile(compiler.getRegex());
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find())
            throw new InvalidExpressionException("Ошибка записи: " + matcher.group());

    }
}

package org.example;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.parser.api.ExpressionParser;
import org.example.parser.api.RegexCompiler;
import org.example.parser.math.MathParser;
import org.example.parser.math.MathRegexCompiler;
import org.example.solver.api.ExpressionSolver;
import org.example.solver.math.MathSolver;
import org.example.token.Token;

import java.util.Stack;

public class Main {
    public static void main(String[] args) throws InvalidTokenException, InvalidExpressionException {
        String expression = "(5 + 6) * (7 - 8 + 9) / 10 + 11 - 12";

        Stack<Token> tokens = parse(expression);
        double result = solve(tokens);

        System.out.println(expression + " = " + result);
    }

    public static Stack<Token> parse(String expression) throws InvalidTokenException, InvalidExpressionException {
        RegexCompiler compiler = new MathRegexCompiler();
        ExpressionParser parser = new MathParser(compiler);
        return parser.parse(expression);
    }

    public static double solve(Stack<Token> tokens) throws InvalidExpressionException, InvalidTokenException {
        ExpressionSolver solver = new MathSolver();
        return solver.solve(tokens);
    }
}
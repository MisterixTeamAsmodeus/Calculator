package org.example.solver.math;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.parser.api.ExpressionParser;
import org.example.parser.api.RegexCompiler;
import org.example.parser.math.MathParser;
import org.example.parser.math.MathRegexCompiler;
import org.example.token.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class MathSolverTest {

    Stack<Token> tokens;
    MathSolver solver;

    @Before
    public void setUp() throws InvalidTokenException, InvalidExpressionException {
        RegexCompiler compiler = new MathRegexCompiler();
        ExpressionParser parser = new MathParser(compiler);
        tokens = parser.parse("(5 + 6) * (7 - 8 + 9) / 10 + 11 - 12");
        solver = new MathSolver();
    }

    @Test
    public void solve() throws InvalidTokenException, InvalidExpressionException {
        Assert.assertEquals(solver.solve(tokens), 7.8, 0.000000000000001);
    }
}
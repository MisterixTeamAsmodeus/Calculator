package org.example.parser.math;

import org.example.exception.InvalidExpressionException;
import org.example.exception.InvalidTokenException;
import org.example.token.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

public class MathParserTest {

    String expression;
    MathParser parser;

    @Before
    public void setUp() {
        expression = "(5 + 6) * (7 - 8 + 9) / 10 + 11 - 12";
        parser = new MathParser(new MathRegexCompiler());
    }

    @Test
    public void parse() throws InvalidTokenException, InvalidExpressionException {
        Stack<Token> tokens = new Stack<>();

        tokens.push(new Token("5"));
        tokens.push(new Token("6"));
        tokens.push(new Token("+"));
        tokens.push(new Token("7"));
        tokens.push(new Token("8"));
        tokens.push(new Token("-"));
        tokens.push(new Token("9"));
        tokens.push(new Token("+"));
        tokens.push(new Token("*"));
        tokens.push(new Token("10"));
        tokens.push(new Token("/"));
        tokens.push(new Token("11"));
        tokens.push(new Token("+"));
        tokens.push(new Token("12"));
        tokens.push(new Token("-"));

        Assert.assertEquals(parser.parse(expression), tokens);
    }
}
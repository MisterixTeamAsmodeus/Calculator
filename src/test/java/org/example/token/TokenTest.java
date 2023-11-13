package org.example.token;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TokenTest {

    Token function, operator, variable, number, constant;

    @Before
    public void setUp(){
        function = new Token("ctg");
        operator = new Token("+");
        variable = new Token("x1");
        number = new Token("56.8");
        constant = new Token("pi");
    }

    @Test
    public void getPrecedence() {
        Assert.assertEquals(operator.getPrecedence(), 1);
        Assert.assertEquals(function.getPrecedence(), -1);
        Assert.assertEquals(variable.getPrecedence(), -1);
    }

    @Test
    public void getToken() {
        Assert.assertEquals(variable.getToken(), "x1");
    }

    @Test
    public void getType() {
        Assert.assertEquals(function.getType(), TokenType.FUNCTION);
        Assert.assertEquals(operator.getType(), TokenType.OPERATOR);
        Assert.assertEquals(variable.getType(), TokenType.VARIABLE);
        Assert.assertEquals(number.getType(), TokenType.NUMBER);
        Assert.assertEquals(constant.getType(), TokenType.CONSTANT);
    }
}
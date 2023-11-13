package org.example.parser.math;

import org.example.parser.api.RegexCompiler;
import org.jetbrains.annotations.NotNull;

import static org.example.token.TokenType.*;

public class MathRegexCompiler implements RegexCompiler {
    @NotNull
    @Override
    public String getRegex() {
        assert OPERATOR.availableList != null;
        assert FUNCTION.availableList != null;

        StringBuilder operator = new StringBuilder("[");
        for (int i = 0; i < OPERATOR.availableList.length; i++) {
            operator.append(OPERATOR.availableList[i]);
            if (i != OPERATOR.availableList.length - 1)
                operator.append(",");
        }
        String operatorAndBracket = operator + ",)]";
        operator.append("]");

        StringBuilder regex = new StringBuilder(
                "\\d+[(]+|" +
                        operator + "{2}|" +
                        "[(]+" + operatorAndBracket + "+|" +
                        operator + "+[)]+"
        );
        for (String f : FUNCTION.availableList) {
            regex.append("|").append(f).append("+").append(operatorAndBracket).append("+");
        }

        return regex.toString();
    }

    @NotNull
    @Override
    public String getDelim() {
        assert OPEN_BRACKET.availableList != null;
        assert OPERATOR.availableList != null;
        assert CLOSE_BRACKET.availableList != null;

        StringBuilder delim = new StringBuilder();
        for (String token : OPEN_BRACKET.availableList)
            delim.append(token);
        for (String token : CLOSE_BRACKET.availableList)
            delim.append(token);
        for (String token : OPERATOR.availableList)
            delim.append(token);

        return delim.toString();
    }
}

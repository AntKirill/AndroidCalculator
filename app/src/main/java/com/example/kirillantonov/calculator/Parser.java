package com.example.kirillantonov.calculator;

import java.math.BigDecimal;

/**
 * Created by Kirill Antonov on 07.11.2016.
 */



public class Parser {

    private String expr;

    private enum Token {PLUS, MINUS, MULT, DIV, NUMBER, BRACKET};
    private Token cur_token;
    private int it = 0;
    private BigDecimal cur_number;


    private void next_token() {
        while (it < expr.length()) {
            switch (expr.charAt(it)) {
                case '+':
                    cur_token = Token.PLUS;
                    break;
                case '-':
                    cur_token = Token.MINUS;
                    break;
                case '*':
                    cur_token = Token.MULT;
                    break;
                case '/':
                    cur_token = Token.DIV;
                    break;
                case '(':
                    cur_token = Token.BRACKET;
                    break;
                case ')':
                    cur_token = Token.BRACKET;
                    break;
                case ' ':
                    it++;
                    continue;
            }
            if ('0' <= expr.charAt(it) && expr.charAt(it) <= '9') {
                cur_token = Token.NUMBER;
                int lastguy = it;
                while (it < expr.length() && ('0' <= expr.charAt(it) && expr.charAt(it) <= '9' || expr.charAt(it) == '.')) {
                    lastguy++;
                }
                cur_number = new BigDecimal(expr.substring(it, ++lastguy));
                it = lastguy;
                return;
            }
            it++;
        }
    }

    private Expression firstLevel() {
        Expression ans = secondLevel();
        while (cur_token == Token.PLUS || cur_token == Token.MINUS) {
            if (cur_token == Token.PLUS) {
                next_token();
                ans = new Add(ans, secondLevel());
            } else if (cur_token == Token.MINUS) {
                next_token();
                ans = new Sub(ans, secondLevel());
            }
        }
        return ans;
    }

    private Expression secondLevel() {
        Expression ans = thirdLevel();
        while (cur_token == Token.DIV || cur_token == Token.MULT) {
            if (cur_token == Token.MULT) {
                next_token();
                ans = new Mult(ans, thirdLevel());
            } else if (cur_token == Token.DIV) {
                next_token();
                ans = new Div(ans, thirdLevel());
            }
        }
        return ans;
    }

    private Expression thirdLevel() {
        Expression ans = null;
        if (cur_token == Token.MINUS) {
            next_token();
            ans = new Sub(new Number(BigDecimal.ZERO), thirdLevel());
        } else if (cur_token == Token.NUMBER){
            ans = new Number(cur_number);
        } else if (cur_token == Token.BRACKET) {
            ans = firstLevel();
            next_token();
        }
        next_token();
        return ans;
    }


    public Expression parse(String expression) {
        this.expr = expression;
        this.it = 0;
        next_token();
        return firstLevel();
    }

}

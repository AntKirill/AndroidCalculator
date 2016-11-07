package com.example.kirillantonov.calculator;

import java.math.BigDecimal;

/**
 * Created by Kirill Antonov on 07.11.2016.
 */

public class Div implements Expression {
    private Expression p, q;

    public Div(Expression p, Expression q) {
        this.p = p;
        this.q = q;
    }

    @Override
    public BigDecimal evaluate() {
        BigDecimal numberQ = q.evaluate();
        if (numberQ.equals(BigDecimal.ZERO)) {
            numberQ = new BigDecimal("0.000001");
        }
        return p.evaluate().divide(numberQ, 5, BigDecimal.ROUND_DOWN);
    }
}

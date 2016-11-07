package com.example.kirillantonov.calculator;

import java.math.BigDecimal;

/**
 * Created by Kirill Antonov on 07.11.2016.
 */
public class Sub implements Expression {
    private Expression p;
    private Expression q;

    public Sub(Expression p, Expression q) {
        this.p = p;
        this.q = q;
    }

    public BigDecimal evaluate() {
        return p.evaluate().subtract(q.evaluate());
    }
}

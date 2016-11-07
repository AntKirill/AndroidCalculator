package com.example.kirillantonov.calculator;

import java.math.BigDecimal;

/**
 * Created by Kirill Antonov on 07.11.2016.
 */

public class Add implements Expression {
    private Expression p, q;

    public Add(Expression p, Expression q) {
        this.p = p;
        this.q = q;
    }

    @Override
    public BigDecimal evaluate() {
        return p.evaluate().add(p.evaluate());
    }
}

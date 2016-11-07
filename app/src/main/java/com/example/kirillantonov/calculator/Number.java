package com.example.kirillantonov.calculator;

import java.math.BigDecimal;

/**
 * Created by Kirill Antonov on 07.11.2016.
 */

public class Number implements Expression {

    private BigDecimal n;

    public Number(BigDecimal n) {
        this.n = n;
    }

    @Override
    public BigDecimal evaluate() {
        return n;
    }
}

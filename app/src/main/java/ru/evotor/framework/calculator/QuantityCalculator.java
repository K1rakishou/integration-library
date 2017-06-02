package ru.evotor.framework.calculator;

import java.math.BigDecimal;

public abstract class QuantityCalculator {

    private static final int QUANTITY_PRECISION = 3;

    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        return value1.add(value2);
    }

    public static BigDecimal subtract(BigDecimal value, BigDecimal subtrahend) {
        return value.subtract(subtrahend);
    }

    public static BigDecimal divide(BigDecimal quantity, BigDecimal divisor) {
        return quantity.divide(divisor, QUANTITY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal quantity, BigDecimal multiplicand) {
        return quantity.multiply(multiplicand).setScale(QUANTITY_PRECISION, BigDecimal.ROUND_HALF_UP);
    }
}
package com.application.common.math

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToInt

fun multiply(
    a: BigDecimal,
    b: BigDecimal,
): BigDecimal = a.multiply(b).setScale(2, RoundingMode.DOWN)

fun subtraction(
    a: BigDecimal,
    b: BigDecimal,
): BigDecimal = (a - b).setScale(2, RoundingMode.DOWN)

fun sum(
    a: BigDecimal,
    b: BigDecimal,
): BigDecimal = (a + b).setScale(2, RoundingMode.DOWN)

fun sum(values: Iterable<BigDecimal>): BigDecimal = values.fold(BigDecimal.ZERO.setScale(2)) { acc, it -> sum(acc, it) }

fun divide(
    a: BigDecimal,
    b: BigDecimal,
): BigDecimal = if (b.signum() == 0) BigDecimal.ZERO.setScale(2) else a.divide(b, 2, RoundingMode.DOWN)

fun percentage(
    part: Int,
    total: Int,
): Double = if (total == 0) 0.0 else (part * 1000.0 / total).roundToInt() / 10.0

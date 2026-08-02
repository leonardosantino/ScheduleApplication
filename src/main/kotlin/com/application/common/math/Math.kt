package com.application.common.math

import java.math.BigDecimal
import java.math.RoundingMode

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

package dev.akarah.interpreter.values

import kotlin.jvm.JvmInline

@JvmInline
value class DecimalLong(val value: Long) {
    constructor(value: Double) : this(value.toLong() * 1000)

    override fun toString(): String {
        return value.toString().replaceFirst(".000", "")
    }

    operator fun plus(other: DecimalLong): DecimalLong {
        return DecimalLong(value + other.value)
    }

    operator fun minus(other: DecimalLong): DecimalLong {
        return DecimalLong(value - other.value)
    }

    operator fun times(other: DecimalLong): DecimalLong {
        return DecimalLong(value * other.value)
    }

    operator fun div(other: DecimalLong): DecimalLong {
        return DecimalLong(value * 1000 / other.value)
    }
}
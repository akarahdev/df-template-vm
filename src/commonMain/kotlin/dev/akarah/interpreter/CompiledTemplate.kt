package dev.akarah.interpreter

import kotlin.jvm.JvmInline

@JvmInline
class CompiledTemplate(
    val bytes: ByteArray,
    val constants: Array<Any?> = emptyArray(),
    val lineVars: MutableMap<String, Int> = mutableMapOf()
) {
}
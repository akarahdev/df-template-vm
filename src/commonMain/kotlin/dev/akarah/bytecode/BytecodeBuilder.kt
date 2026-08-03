package dev.akarah.bytecode

import dev.akarah.interpreter.CompiledTemplate

class BytecodeBuilder {
    private val bytecode = mutableListOf<Byte>()

    fun build(): CompiledTemplate = CompiledTemplate(bytecode.toByteArray())

    fun _return() {
        bytecode.add(Opcodes.RETURN)
    }
}
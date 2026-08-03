package dev.akarah.bytecode

import dev.akarah.interpreter.CompiledTemplate

class BytecodeBuilder {
    private val bytecode = mutableListOf<Byte>()
    private val constants = mutableListOf<Any?>()

    fun build(): CompiledTemplate = CompiledTemplate(
        bytecode.toByteArray(),
        this.constants.toTypedArray()
    )

    fun _return() {
        bytecode.add(Opcodes.RETURN)
    }

    fun mov(register: Byte, constant: Any?) {
        bytecode.add(Opcodes.MOV_CONSTANT)
        bytecode.add(register)
        val idx = if(!constants.contains(constant)) {
            constants.add(constant)
            constants.size - 1
        } else {
            constants.indexOf(constant)
        }
        bytecode.add(idx.toByte())
    }

    fun dumpRegisters() {
        bytecode.add(Opcodes.DUMP_REGISTERS)
    }
}
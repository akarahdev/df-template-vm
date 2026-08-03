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

    fun storeLineVar(idx: Byte, register: Byte) {
        bytecode.add(Opcodes.STORE_LINE_VAR_IDX)
        bytecode.add(register)
        bytecode.add(idx)
    }

    fun readLineVar(idx: Byte, register: Byte) {
        bytecode.add(Opcodes.LOAD_LINE_VAR_IDX)
        bytecode.add(register)
        bytecode.add(idx)
    }

    fun dumpRegisters() {
        bytecode.add(Opcodes.DUMP_REGISTERS)
    }
}
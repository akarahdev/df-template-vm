package dev.akarah.bytecode

import dev.akarah.action.ActionRegistry
import dev.akarah.action.kinds.BaseAction
import dev.akarah.interpreter.CompiledTemplate

class BytecodeBuilder {
    private val bytecode = mutableListOf<Byte>()
    private val constants = mutableListOf<Any?>()
    private var lineVars = mutableMapOf<String, Int>()

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

    fun getLineVarIdx(name: String): Int {
        if(!lineVars.containsKey(name)) {
            lineVars[name] = lineVars.size
        }
        return lineVars[name]!!
    }

    fun storeLineVar(name: String, register: Byte) {
        bytecode.add(Opcodes.STORE_LINE_VAR_IDX)
        bytecode.add(register)
        bytecode.add(getLineVarIdx(name).toByte())
    }

    fun readLineVar(name: String, register: Byte) {
        bytecode.add(Opcodes.LOAD_LINE_VAR_IDX)
        bytecode.add(register)
        bytecode.add(getLineVarIdx(name).toByte())
    }

    fun callExtern(action: BaseAction) {
        bytecode.add(Opcodes.CALL_EXTERN)
        bytecode.add(ActionRegistry.actionsByTypeToIndex[action]!!.toByte())
    }

    fun createVarargs(register: Byte) {
        bytecode.add(Opcodes.CREATE_VARARGS)
        bytecode.add(register)
    }

    fun storeRegisterToVarargs(destVarargsRegister: Byte, srcContentRegister: Byte) {
        bytecode.add(Opcodes.STORE_REGISTER_TO_VARARGS)
        bytecode.add(destVarargsRegister)
        bytecode.add(srcContentRegister)
    }

    fun dumpRegisters() {
        bytecode.add(Opcodes.DUMP_REGISTERS)
    }
}
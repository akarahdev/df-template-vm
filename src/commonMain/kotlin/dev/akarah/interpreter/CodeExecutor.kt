package dev.akarah.interpreter

import dev.akarah.action.ActionRegistry
import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.bytecode.Opcodes
import dev.akarah.util.resize

class CodeExecutor(val context: ExecutorContext) {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun execute(code: CompiledTemplate) {
        val bytecode = code.bytes
        val registers = arrayOfNulls<Any?>(16)
        val lineVars = arrayOfNulls<Any?>(16)
        var pc = 0
        while(true) {
            when(bytecode[pc]) {
                Opcodes.RETURN -> break
                Opcodes.MOV_CONSTANT -> {
                    val registerIdx = bytecode[++pc]
                    val idx = bytecode[++pc]
                    val constant = code.constants[idx.toInt()]
                    registers[registerIdx.toInt()] = constant
                    pc++
                }
                Opcodes.DUMP_REGISTERS -> {
                    println(registers.contentToString() + "\n")
                    pc++
                }
                Opcodes.STORE_LINE_VAR_IDX -> {
                    val registerIdx = bytecode[++pc]
                    val varSlotIdx = bytecode[++pc]
                    lineVars[varSlotIdx.toInt()] = registers[registerIdx.toInt()]
                    pc++
                }
                Opcodes.LOAD_LINE_VAR_IDX -> {
                    val registerIdx = bytecode[++pc]
                    val varSlotIdx = bytecode[++pc]
                    registers[registerIdx.toInt()] = lineVars[varSlotIdx.toInt()]
                    pc++
                }
                Opcodes.CALL_EXTERN -> {
                    val externIdx = bytecode[++pc]
                    (ActionRegistry.actions[externIdx.toInt()] as ImperativeAction).execute(
                        this,
                        registers,
                        lineVars
                    )
                    pc++
                }
                Opcodes.CREATE_VARARGS -> {
                    val registerIdx = bytecode[++pc]
                    val size = bytecode[++pc]
                    registers[registerIdx.toInt()] = arrayOfNulls<Any?>(size.toInt())
                    pc++
                }
                Opcodes.STORE_REGISTER_TO_VARARGS -> {
                    val destVarargsRegister = bytecode[++pc]
                    val srcContentRegister = bytecode[++pc]
                    val idx = bytecode[++pc]
                    @Suppress("UNCHECKED_CAST")
                    (registers[destVarargsRegister.toInt()] as Array<Any?>)[idx.toInt()] = registers[srcContentRegister.toInt()]
                    pc++
                }
                else -> throw UnsupportedOperationException("Unknown opcode: ${bytecode[pc]}")
            }
        }
    }
}
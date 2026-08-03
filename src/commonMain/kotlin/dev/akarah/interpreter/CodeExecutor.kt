package dev.akarah.interpreter

import dev.akarah.bytecode.Opcodes
import dev.akarah.util.resize

class CodeExecutor(val context: ExecutorContext) {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun execute(code: CompiledTemplate) {
        val bytecode = code.bytes
        val registers = arrayOfNulls<Any?>(10)
        var pc = 0
        while(true) {
            when(bytecode[pc]) {
                Opcodes.RETURN -> break
                Opcodes.MOV_CONSTANT -> {
                    val registerIdx = bytecode[++pc]
                    val idx = bytecode[++pc]
                    registers[registerIdx.toInt()] = code.constants[idx.toInt()]
                    pc++
                }
                Opcodes.DUMP_REGISTERS -> {
                    println(registers.contentToString() + "\n")
                    pc++
                }
            }
        }
    }
}
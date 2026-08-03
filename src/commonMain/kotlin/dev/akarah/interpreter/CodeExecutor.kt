package dev.akarah.interpreter

import dev.akarah.bytecode.Opcodes

class CodeExecutor(val context: ExecutorContext) {
    fun execute(code: CompiledTemplate) {
        val bytecode = code.bytes
        var pc = 0
        while(true) {
            when(bytecode[pc]) {
                Opcodes.RETURN -> break
            }
            pc++
        }
    }
}
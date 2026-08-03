package dev.akarah.bytecode

object Opcodes {
    const val RETURN: Byte = 0
    const val MOV_CONSTANT: Byte = 1
    const val STORE_LINE_VAR_IDX: Byte = 2
    const val LOAD_LINE_VAR_IDX: Byte = 3
    const val CALL_EXTERN: Byte = 4
    const val CREATE_VARARGS: Byte = 5
    const val STORE_REGISTER_TO_VARARGS: Byte = 6
    const val DUMP_REGISTERS: Byte = -127
}
package dev.akarah.action.setvar

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarMul : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "x"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(0, ParameterType.VARIABLE),
            ParameterNode.Varargs(1, ParameterType.NUMBER)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        var out = DecimalLong(1000)
        @Suppress("UNCHECKED_CAST")
        for(value in registers[1] as Array<Any?>) {
            out *= value as DecimalLong
        }
        lineVars[registers[0] as Int] = out
    }
}
package dev.akarah.action.setvar

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarDiv : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "/"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(0, ParameterType.VARIABLE),
            ParameterNode.Singleton(1, ParameterType.VARIABLE),
            ParameterNode.Varargs(2, ParameterType.NUMBER)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        @Suppress("UNCHECKED_CAST")
        val inpArr = registers[2] as Array<Any?>
        var out = inpArr[0] as DecimalLong
        for(i in 1 until inpArr.size) {
            out /= inpArr[i] as DecimalLong
        }
        lineVars[registers[0] as Int] = out
    }
}
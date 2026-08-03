package dev.akarah.action.setvar.str

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarRepeatString : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "RepeatString"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(0, ParameterType.VARIABLE),
            ParameterNode.Singleton(1, ParameterType.STRING),
            ParameterNode.Singleton(2, ParameterType.NUMBER)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        val varIdx = registers[0] as Int
        val baseStr = registers[1] as String
        lineVars[varIdx] = baseStr.repeat((registers[2] as DecimalLong).toInt())
    }
}
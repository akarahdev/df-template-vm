package dev.akarah.action.setvar.str

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarReplaceString : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "ReplaceString"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(2, ParameterType.VARIABLE),
            ParameterNode.Singleton(3, ParameterType.STRING, null),
            ParameterNode.Singleton(4, ParameterType.STRING),
            ParameterNode.Singleton(5, ParameterType.STRING)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        val varIdx = registers[2] as Int
        val baseStr = registers[3] as? String ?: lineVars[varIdx] as String
        lineVars[varIdx] = baseStr.replace(registers[4] as String, registers[5] as String)
    }
}
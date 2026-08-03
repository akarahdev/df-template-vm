package dev.akarah.action.setvar.str

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarStringLength : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "StringLength"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(0, ParameterType.VARIABLE),
            ParameterNode.Singleton(1, ParameterType.STRING)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        val varIdx = registers[0] as Int
        val baseStr = registers[1] as? String ?: lineVars[varIdx] as String
        lineVars[varIdx] = DecimalLong(baseStr.length.toLong() * 1000)
    }
}
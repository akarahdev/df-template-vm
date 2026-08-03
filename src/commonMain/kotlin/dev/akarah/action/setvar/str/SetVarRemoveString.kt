package dev.akarah.action.setvar.str

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlockType

object SetVarRemoveString : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "RemoveString"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(1, ParameterType.VARIABLE),
            ParameterNode.Singleton(2, ParameterType.STRING, null),
            ParameterNode.Singleton(3, ParameterType.STRING)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        val varIdx = registers[1] as Int
        val baseStr = registers[2] as? String ?: lineVars[varIdx] as String
        lineVars[varIdx] = baseStr.replace(
            registers[3] as String,
            ""
        )
    }
}
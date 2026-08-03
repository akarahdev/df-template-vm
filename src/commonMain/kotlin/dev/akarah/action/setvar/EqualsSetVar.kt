package dev.akarah.action.setvar

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlock
import dev.akarah.template.codeblock.CodeBlockType

object EqualsSetVar : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.SET_VAR
    override val name: String = "="
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Singleton(0, ParameterType.VARIABLE),
            ParameterNode.Singleton(1, ParameterType.ANY)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        println("Setting var ${registers[0]} to ${registers[1]}")
        lineVars[registers[0] as Int] = registers[1]
    }
}
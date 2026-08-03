package dev.akarah.action.setvar

import dev.akarah.action.kinds.ImperativeAction
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.parsing.ParameterNode
import dev.akarah.parsing.ParameterSet
import dev.akarah.parsing.ParameterType
import dev.akarah.template.codeblock.CodeBlock
import dev.akarah.template.codeblock.CodeBlockType

object ControlPrintDebug : ImperativeAction {
    override val codeblock: CodeBlockType = CodeBlockType.CONTROL
    override val name: String = "PrintDebug"
    override val parameters: ParameterSet
        get() = ParameterSet(
            ParameterNode.Varargs(5, ParameterType.ANY)
        )

    override fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    ) {
        val str = (registers[5] as List<Any?>).joinToString(" ")
        println(str)
    }
}
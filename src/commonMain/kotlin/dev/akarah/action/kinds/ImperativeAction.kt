package dev.akarah.action.kinds

import dev.akarah.interpreter.CodeExecutor
import dev.akarah.parsing.ParameterSet

interface ImperativeAction : BaseAction {
    fun execute(
        executor: CodeExecutor,
        registers: Array<Any?>,
        lineVars: Array<Any?>
    )
}
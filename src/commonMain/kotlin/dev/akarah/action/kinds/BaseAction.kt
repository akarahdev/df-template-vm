package dev.akarah.action.kinds

import dev.akarah.interpreter.CodeExecutor
import dev.akarah.parsing.ParameterSet
import dev.akarah.template.codeblock.CodeBlockType

interface BaseAction {
    val codeblock: CodeBlockType
    val name: String
    val parameters: ParameterSet
}
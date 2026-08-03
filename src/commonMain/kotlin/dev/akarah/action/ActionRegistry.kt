package dev.akarah.action

import dev.akarah.action.kinds.BaseAction
import dev.akarah.action.setvar.EqualsSetVar
import dev.akarah.template.codeblock.CodeBlock
import dev.akarah.template.codeblock.CodeBlockType

object ActionRegistry {
    val actions: List<BaseAction> = listOf(
        EqualsSetVar
    )

    val actionsByKeyed: Map<Pair<CodeBlockType, String>, BaseAction> =
        actions.associateBy { Pair(it.codeblock, it.name) }

    val actionsByTypeToIndex: Map<BaseAction, Int> =
        actions.withIndex().associate { it.value to it.index }
}
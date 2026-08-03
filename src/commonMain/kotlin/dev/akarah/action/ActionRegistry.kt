package dev.akarah.action

import dev.akarah.action.kinds.BaseAction
import dev.akarah.action.setvar.SetVarAdd
import dev.akarah.action.setvar.ControlPrintDebug
import dev.akarah.action.setvar.SetVarEquals
import dev.akarah.template.codeblock.CodeBlockType

object ActionRegistry {
    val actions: List<BaseAction> = listOf(
        SetVarEquals, SetVarAdd, ControlPrintDebug
    )

    val actionsByKeyed: Map<Pair<CodeBlockType, String>, BaseAction> =
        actions.associateBy { Pair(it.codeblock, it.name) }

    val actionsByTypeToIndex: Map<BaseAction, Int> =
        actions.withIndex().associate { it.value to it.index }
}
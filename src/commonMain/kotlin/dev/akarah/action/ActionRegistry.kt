package dev.akarah.action

import dev.akarah.action.kinds.BaseAction
import dev.akarah.action.setvar.num.SetVarAdd
import dev.akarah.action.setvar.ControlPrintDebug
import dev.akarah.action.setvar.num.SetVarDiv
import dev.akarah.action.setvar.SetVarEquals
import dev.akarah.action.setvar.num.SetVarMul
import dev.akarah.action.setvar.num.SetVarSub
import dev.akarah.action.setvar.str.SetVarReplaceString
import dev.akarah.action.setvar.str.SetVarSubstring
import dev.akarah.template.codeblock.CodeBlockType

object ActionRegistry {
    val actions: List<BaseAction> = listOf(
        SetVarEquals, SetVarAdd, SetVarSub, SetVarMul, SetVarDiv, ControlPrintDebug,

        SetVarReplaceString, SetVarSubstring
    )

    val actionsByKeyed: Map<Pair<CodeBlockType, String>, BaseAction> =
        actions.associateBy { Pair(it.codeblock, it.name) }

    val actionsByTypeToIndex: Map<BaseAction, Int> =
        actions.withIndex().associate { it.value to it.index }
}
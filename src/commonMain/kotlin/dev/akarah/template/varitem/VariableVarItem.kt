package dev.akarah.template.varitem

import dev.akarah.template.varitem.data.NamedData
import dev.akarah.template.varitem.data.VariableData
import dev.akarah.template.varitem.data.VariableScope
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("var")
data class VariableVarItem(val data: VariableData) : VarItem {
    constructor(data: String) : this(VariableData(data, VariableScope.LINE))
    constructor(data: String, scope: VariableScope) : this(VariableData(data, scope))
}
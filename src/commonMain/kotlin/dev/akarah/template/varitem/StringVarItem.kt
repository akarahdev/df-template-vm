package dev.akarah.template.varitem

import dev.akarah.template.varitem.data.NamedData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("txt")
data class StringVarItem(val data: NamedData) : VarItem {
    constructor(data: String) : this(NamedData(data))
}
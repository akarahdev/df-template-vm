package dev.akarah.template.slot

import dev.akarah.template.varitem.VarItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("singleton")
data class SingletonElement(val data: Data, override val slot: Int) : SlotElement {
    @Serializable
    data class Data(
        @SerialName("code_item")
        val codeItem: VarItem
    ) {
    }
}
package dev.akarah.template.slot

import dev.akarah.template.varitem.VarItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("vararg")
data class VarargElement(val data: Data, override val slot: Int) : SlotElement {
    @Serializable
    data class Data(
        val selection: Selection
    ) {
        @Serializable
        data class Selection(
            val mode: PluralElementMode,
            val items: List<VarItem>
        )
    }

    enum class PluralElementMode {
        INLINED
    }
}
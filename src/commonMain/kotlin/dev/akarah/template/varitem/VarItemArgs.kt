package dev.akarah.template.varitem

import kotlinx.serialization.Serializable

@Serializable
data class VarItemArgs(
    val items: List<SlotData>
) {
    @Serializable
    data class SlotData(
        val item: VarItem,
        val slot: Int
    )
}
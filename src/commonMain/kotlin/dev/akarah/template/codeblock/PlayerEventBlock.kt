package dev.akarah.template.codeblock

import dev.akarah.template.slot.SlotElementData
import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("event")
data class PlayerEventBlock(
    val action: String,
    val slots: SlotElementData = SlotElementData(),
    val args: VarItemArgs = VarItemArgs()
) : NormalBlock {

}

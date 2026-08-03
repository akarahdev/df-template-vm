package dev.akarah.template.codeblock

import dev.akarah.template.slot.SlotElementData
import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("set_var")
data class SetVariableBlock(
    val action: String,
    val slots: SlotElementData = SlotElementData(),
    val args: VarItemArgs = VarItemArgs()
) : NormalBlock {

}

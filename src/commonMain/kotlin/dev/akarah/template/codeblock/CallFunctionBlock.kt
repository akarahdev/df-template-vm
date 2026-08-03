package dev.akarah.template.codeblock

import dev.akarah.template.slot.SlotElement
import dev.akarah.template.slot.SlotElementData
import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("call_func")
data class CallFunctionBlock(
    val data: String,
    val slots: SlotElementData = SlotElementData(),
    val args: VarItemArgs = VarItemArgs()
) : NormalBlock {

}

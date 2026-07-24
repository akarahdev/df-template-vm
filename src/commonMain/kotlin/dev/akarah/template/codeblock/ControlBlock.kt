package dev.akarah.template.codeblock

import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("control")
data class ControlBlock(
    val action: String,
    val args: VarItemArgs
) : NormalBlock {

}

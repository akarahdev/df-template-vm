package dev.akarah.template.codeblock

import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("process")
data class ProcessBlock(
    val data: String,
    val args: VarItemArgs
) : NormalBlock {

}

package dev.akarah.template.codeblock

import dev.akarah.template.varitem.VarItemArgs
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@SerialName("call_func")
data class CallFunctionBlock(
    val data: String,
    val args: VarItemArgs
) : NormalBlock {

}

package dev.akarah.template.codeblock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
data class BracketBlock(
    val id: String = "bracket",
    @SerialName("direct")
    val direction: BracketDirection,
    val type: BracketType,
) : CodeBlock {

}
package dev.akarah.template.codeblock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BracketType {
    @SerialName("norm")
    NORMAL,
    @SerialName("repeat")
    REPEAT
}
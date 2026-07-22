package dev.akarah.template.codeblock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BracketDirection {
    @SerialName("open")
    OPEN,
    @SerialName("close")
    CLOSE
}
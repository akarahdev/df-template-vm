package dev.akarah.template.varitem.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VariableScope {
    @SerialName("line")
    LINE,
    @SerialName("local")
    LOCAL,
    @SerialName("unsaved")
    GAME,
    @SerialName("saved")
    SAVED
}
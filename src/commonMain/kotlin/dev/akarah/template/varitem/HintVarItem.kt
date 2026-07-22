package dev.akarah.template.varitem

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("hint")
data class HintVarItem(val id: String) : VarItem
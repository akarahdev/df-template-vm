package dev.akarah.template.varitem.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockTagData(
    val option: String,
    val tag: String,
    val action: String,
    @SerialName("block")
    val codeBlock: String
)
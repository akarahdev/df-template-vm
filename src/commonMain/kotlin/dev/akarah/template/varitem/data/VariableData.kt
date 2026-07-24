package dev.akarah.template.varitem.data

import kotlinx.serialization.Serializable

@Serializable
data class VariableData(
    val name: String,
    val scope: VariableScope
)
package dev.akarah.template.slot

import kotlinx.serialization.Serializable

@Serializable
data class SlotElementData(val elements: List<SlotElement> = emptyList()) {
}
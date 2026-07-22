package dev.akarah.template.varitem

import dev.akarah.template.varitem.data.BlockTagData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@SerialName("bl_tag")
data class BlockTagVarItem(val data: BlockTagData) : VarItem

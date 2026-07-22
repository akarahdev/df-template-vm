package dev.akarah.template.codeblock

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(with = CodeBlock.CodeBlockSerializer::class)
sealed interface CodeBlock {
    object CodeBlockSerializer : JsonContentPolymorphicSerializer<CodeBlock>(CodeBlock::class) {
        @Suppress("UNCHECKED_CAST")
        override fun selectDeserializer(element: JsonElement): DeserializationStrategy<CodeBlock> {
            return when (element.jsonObject["id"]?.jsonPrimitive?.content) {
                "block" -> NormalBlock.serializer()
                "bracket" -> BracketBlock.serializer()
                else -> error("Unknown code block id")
            } as DeserializationStrategy<CodeBlock>
        }
    }
}
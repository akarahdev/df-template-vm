package dev.akarah.template

import dev.akarah.template.codeblock.CodeBlock
import dev.akarah.template.varitem.VarItem
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CodeTemplateData(
    val blocks: List<CodeBlock>
) {
    companion object {
        fun parse(input: String): CodeTemplateData {
            val json = Json {
                ignoreUnknownKeys = true
            }
            return json.decodeFromString(input)
        }
    }

}

package dev.akarah.bytecode

import dev.akarah.action.ActionRegistry
import dev.akarah.action.kinds.BaseAction
import dev.akarah.interpreter.CompiledTemplate
import dev.akarah.parsing.ParameterSet
import dev.akarah.template.CodeTemplateData
import dev.akarah.template.codeblock.CodeBlockType
import dev.akarah.template.codeblock.ControlBlock
import dev.akarah.template.codeblock.SetVariableBlock
import dev.akarah.template.slot.SlotElementData

class TemplateCompiler {
    companion object {
        fun compile(template: CodeTemplateData): CompiledTemplate {
            return TemplateCompiler().compile(template)
        }
    }
    val builder = BytecodeBuilder()

    fun compile(templateData: CodeTemplateData): CompiledTemplate {
        for(block in templateData.blocks) {
            when(block) {
                is SetVariableBlock -> {
                    val action = findAction(CodeBlockType.SET_VAR, block.action)
                    compileParameters(action.parameters, block.slots, builder)
                    this.builder.callExtern(action)
                }
                is ControlBlock -> {
                    val action = findAction(CodeBlockType.CONTROL, block.action)
                    compileParameters(action.parameters, block.slots, builder)
                    this.builder.callExtern(action)
                }
                else -> {}
            }
        }
        this.builder._return()
        return this.builder.build()
    }

    fun findAction(
        codeBlockType: CodeBlockType,
        action: String
    ): BaseAction {
        return ActionRegistry.actionsByKeyed[Pair(codeBlockType, action)]!!
    }

    fun compileParameters(
        parameters: ParameterSet,
        chestData: SlotElementData,
        bytecodeBuilder: BytecodeBuilder
    ) {
        for(slot in chestData.elements) {
            val param = parameters.paramByIndex(slot.slot)
            param.parse(chestData, bytecodeBuilder)
        }
    }
}
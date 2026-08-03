package dev.akarah.parsing

import dev.akarah.bytecode.BytecodeBuilder
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.template.slot.SingletonElement
import dev.akarah.template.slot.SlotElementData
import dev.akarah.template.slot.VarargElement
import dev.akarah.template.varitem.NumberVarItem
import dev.akarah.template.varitem.VarItem
import dev.akarah.template.varitem.VariableVarItem

sealed interface ParameterNode {
    companion object {
        fun varItemToBytecode(
            varItem: VarItem,
            slot: Byte,
            bytecode: BytecodeBuilder,
            type: ParameterType
        ) {
            when(varItem) {
                is VariableVarItem -> {
                    if(type == ParameterType.VARIABLE) {
                        bytecode.mov(slot, bytecode.getLineVarIdx(varItem.data.name))
                    } else {
                        bytecode.readLineVar(varItem.data.name, slot)
                    }
                }
                is NumberVarItem -> {
                    bytecode.mov(slot, DecimalLong(varItem.data.name.toDouble()))
                }
                else -> throw UnsupportedOperationException("Unsupported var item type: ${varItem::class.simpleName}")
            }
        }
    }

    val slot: Int

    fun parse(data: SlotElementData, bytecode: BytecodeBuilder)

    class Singleton(
        override val slot: Int,
        val type: ParameterType
    ) : ParameterNode {
        override fun parse(data: SlotElementData, bytecode: BytecodeBuilder) {
            val element = data.elements.find { it.slot == slot } as? SingletonElement ?: return

            varItemToBytecode(element.data.codeItem, slot.toByte(), bytecode, type)
        }
    }

    class Varargs(
        override val slot: Int,
        val type: ParameterType
    ) : ParameterNode {
        override fun parse(data: SlotElementData, bytecode: BytecodeBuilder) {
            val element = data.elements.find { it.slot == slot } as? VarargElement ?: return
            bytecode.createVarargs(slot.toByte())

            when(element.data.selection.mode) {
                VarargElement.PluralElementMode.INLINED -> {
                    for(item in element.data.selection.items!!) {
                        varItemToBytecode(item, 127, bytecode, type)
                        bytecode.storeRegisterToVarargs(
                            slot.toByte(),
                            127
                        )
                    }
                }
                VarargElement.PluralElementMode.CODE_ITEM -> {
                    varItemToBytecode(element.data.selection.codeItem!!, 127, bytecode, type)
                    bytecode.storeRegisterToVarargs(
                        slot.toByte(),
                        127
                    )
                }
            }
        }
    }
}
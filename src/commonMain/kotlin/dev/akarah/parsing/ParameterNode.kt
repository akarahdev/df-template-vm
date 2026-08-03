package dev.akarah.parsing

import dev.akarah.bytecode.BytecodeBuilder
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.template.slot.SingletonElement
import dev.akarah.template.slot.SlotElementData
import dev.akarah.template.slot.VarargElement
import dev.akarah.template.varitem.NumberVarItem
import dev.akarah.template.varitem.StringVarItem
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
                is StringVarItem -> {
                    bytecode.mov(slot, varItem.data.name)
                }
                else -> throw UnsupportedOperationException("Unsupported var item type: ${varItem::class.simpleName}")
            }
        }
    }

    val slot: Int

    fun parse(data: SlotElementData, bytecode: BytecodeBuilder)

    class Singleton(
        override val slot: Int,
        val type: ParameterType,
        val fallback: Any? = null
    ) : ParameterNode {
        override fun parse(data: SlotElementData, bytecode: BytecodeBuilder) {
            val tryFind = data.elements.find { it.slot == slot }

            if(tryFind == null) {
                bytecode.mov(slot.toByte(), fallback)
                return
            }

            val element = tryFind as? SingletonElement ?: return

            varItemToBytecode(element.data.codeItem, slot.toByte(), bytecode, type)
        }
    }

    class Varargs(
        override val slot: Int,
        val type: ParameterType,
        val fallback: Any? = null
    ) : ParameterNode {
        override fun parse(data: SlotElementData, bytecode: BytecodeBuilder) {
            val tryFind = data.elements.find { it.slot == slot }

            if(tryFind == null) {
                bytecode.createVarargs(slot.toByte(), 1.toByte())
                bytecode.mov(12, fallback)
                bytecode.storeRegisterToVarargs(
                    slot.toByte(),
                    12,
                    0
                )
                return
            }

            val element = tryFind as? VarargElement ?: return

            when(element.data.selection.mode) {
                VarargElement.PluralElementMode.INLINED -> {
                    var slotIdx = 0.toByte()
                    bytecode.createVarargs(slot.toByte(), element.data.selection.items!!.size.toByte())
                    for(item in element.data.selection.items) {
                        varItemToBytecode(item, 12, bytecode, type)
                        bytecode.storeRegisterToVarargs(
                            slot.toByte(),
                            12,
                            slotIdx
                        )
                        slotIdx++
                    }
                }
                VarargElement.PluralElementMode.CODE_ITEM -> {
                    bytecode.createVarargs(slot.toByte(), 1.toByte())
                    varItemToBytecode(element.data.selection.codeItem!!, 12, bytecode, type)
                    bytecode.storeRegisterToVarargs(
                        slot.toByte(),
                        12,
                        0
                    )
                }
            }
        }
    }
}
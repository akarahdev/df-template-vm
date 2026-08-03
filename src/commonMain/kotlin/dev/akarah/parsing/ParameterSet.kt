package dev.akarah.parsing

class ParameterSet(val parameters: List<ParameterNode>) {
    constructor() : this(emptyList())
    constructor(vararg parameters: ParameterNode) : this(parameters.toList())

    fun paramByIndex(index: Int): ParameterNode {
        return this.parameters[index]
    }
}
package dev.akarah.util

inline fun <reified T> Array<T?>.resize(newSize: Int): Array<T?> {
    return Array(newSize) { idx ->
        if(idx < size) this[idx]
        else null
    }
}
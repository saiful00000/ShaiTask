package com.shaiful.shaitask.modules.notes.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
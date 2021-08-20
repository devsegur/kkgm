package me.evertonfs.domain.model

open class Table(id: Long?, name: String?, reference: List<Column>?) {
    constructor() : this(null, "", emptyList())
}

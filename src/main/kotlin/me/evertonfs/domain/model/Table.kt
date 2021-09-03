package me.evertonfs.domain.model

open class Table(open var id: Long?,open var name: String?, open var reference: List<Column>?) {
    constructor() : this(null, "", emptyList())
}

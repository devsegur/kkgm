package me.evertonfs.domain.model

import me.evertonfs.application.entity.TableEntity

open class Column( open var id: Long?, open var name: String?, open var table: Table?) {
    constructor() : this(0L, "", Table(0L, "", emptyList()))
}
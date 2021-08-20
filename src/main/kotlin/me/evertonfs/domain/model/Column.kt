package me.evertonfs.domain.model

import me.evertonfs.application.entity.TableEntity

open class Column(id: Long?, name: String?, table: Table?) {
    constructor() : this(0L, "", Table(0L, "", emptyList()))
}
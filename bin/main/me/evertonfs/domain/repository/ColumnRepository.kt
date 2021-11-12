package me.evertonfs.domain.repository

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.domain.model.Column

interface ColumnRepository {

    fun findAllImpl(): List<Column>

    fun save(column: Column): Column
}
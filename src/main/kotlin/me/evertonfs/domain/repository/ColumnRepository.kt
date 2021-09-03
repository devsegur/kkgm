package me.evertonfs.domain.repository

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.domain.model.Column

interface ColumnRepository {

    fun findAll(): MutableList<ColumnEntity>

    fun save(column: ColumnEntity): ColumnEntity
}
package me.evertonfs.infra.output

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.domain.repository.ColumnRepository

class ColumnRepositoryImpl : ColumnRepository {
    override fun findAll(): MutableList<ColumnEntity> {
        TODO("Not yet implemented")
    }

    override fun save(column: ColumnEntity): ColumnEntity {
        TODO("Not yet implemented")
    }
}
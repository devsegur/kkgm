package me.evertonfs.domain.service

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.domain.model.Column
import me.evertonfs.domain.model.Table
import me.evertonfs.domain.repository.ColumnRepository
import me.evertonfs.domain.repository.TableRepository
import java.time.LocalDateTime

class TableService(private val repository: TableRepository, private val repo: ColumnRepository) {

    fun listTables(): List<Table> {
        val column = ColumnEntity(null, "coluna" + LocalDateTime.now().nano, null)

        repo.save(column)

        val result = TableEntity(null, "nombre indefinido " + LocalDateTime.now().nano.toString(), listOf(column))

        repository.save(result)

        column.table = result

        return repository.findAll().map { tableEntity ->
            Table(
                tableEntity.id,
                tableEntity.name,
                tableEntity.reference?.map { columnEntity ->
                    Column(
                        columnEntity.id,
                        columnEntity.name,
                        null
                    )
                })
        }
    }
}
package me.evertonfs.domain.service

import me.evertonfs.domain.model.Column
import me.evertonfs.domain.model.Table
import me.evertonfs.domain.repository.ColumnRepository
import me.evertonfs.domain.repository.TableRepository
import java.time.LocalDateTime

class TableService(private val repository: TableRepository, private val repo: ColumnRepository) {

    fun listTables(): List<Table?> {
        val column = Column(null, "coluna" + LocalDateTime.now().nano, null)

        repo.save(column)

        val result = Table(null, "nombre indefinido " + LocalDateTime.now().nano.toString(), listOf(column))

        repository.save(result)

        column.table = result

        return repository.findAllImpl()
    }
}
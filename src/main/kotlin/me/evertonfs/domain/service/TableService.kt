package me.evertonfs.domain.service

import me.evertonfs.domain.model.Table
import me.evertonfs.domain.repository.ColumnRepository
import me.evertonfs.domain.repository.TableRepository
import reactor.core.publisher.Flux

class TableService(private val repository: TableRepository, private val repo: ColumnRepository) {

    fun listTables(): List<Table?> {
        return repository.findAllImpl()
    }

    fun saveTable(table: Table): Table {
        return repository.save(table)!!
    }

    fun saveAllTables(tables: List<Table>): Flux<Table> {
        return Flux.fromIterable(tables.map { repository.save(it) }.toCollection(arrayListOf()))
    }


}
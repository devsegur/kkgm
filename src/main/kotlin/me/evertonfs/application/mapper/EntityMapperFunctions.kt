package me.evertonfs.application.mapper

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.domain.model.Column
import me.evertonfs.domain.model.Table

fun tableMapper(table: Table?): Pair<Table?, TableEntity?> {
    return table to table?.reference?.map { column -> columnWithoutTableEntityMapper(column).second }?.let {
        TableEntity(
            id = table.id,
            name = table.name,
            reference = it)
    }
}

fun tableMapper(table: TableEntity?): Pair<TableEntity?, Table?> {
    return table to Table(
        id = table?.id,
        name = table?.name,
        reference = table?.reference?.map { column -> columnWithoutTableEntityMapper(column).second })
}

fun columnWithoutTableEntityMapper(column: ColumnEntity?): Pair<ColumnEntity?, Column> =
    column to Column(column?.id, column?.name, null)

fun columnWithoutTableEntityMapper(column: Column): Pair<Column?, ColumnEntity> =
    column to ColumnEntity(column.id, column.name, null)

fun columnWithTableEntityMapper(column: Column?) = column to ColumnEntity(
    column?.id, column?.name,
    column?.table?.let { tableMapper(it) }?.second
)
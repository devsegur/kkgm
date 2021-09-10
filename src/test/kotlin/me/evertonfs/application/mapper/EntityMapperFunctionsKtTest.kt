package me.evertonfs.application.mapper

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.domain.model.Column
import me.evertonfs.domain.model.Table
import org.junit.jupiter.api.Test

internal class EntityMapperFunctionsKtTest {

    @Test
    fun tableMapper() {

        val tableName= "potatoes"
        val tableId = 14L
        val columnId = 16L
        val columnName = "fries"

        val columnEntityList = mutableListOf(ColumnEntity(
            id = columnId,
            name = columnName,
            table = null
        ))

        val expectedTable = TableEntity(
            id = tableId,
            name =  tableName,
            reference = columnEntityList
        )

        val columnList = mutableListOf(Column(id = null, name = "fries", table = null))
        val input = Table(id = tableId, name = tableName , reference = columnList)

        val tableMapper = tableMapper(input).second

        assert(tableMapper?.equals(expectedTable) ?: false )

    }

    @Test
    fun testTableMapper() {
    }

    @Test
    fun columnWithoutTableEntityMapper() {
    }

    @Test
    fun testColumnWithoutTableEntityMapper() {
    }

    @Test
    fun columnWithTableEntityMapper() {
    }
}
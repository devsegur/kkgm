package me.evertonfs.infra.output

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.application.mapper.tableMapper
import me.evertonfs.domain.model.Table
import me.evertonfs.domain.repository.TableRepository

@Repository
abstract class TableRepositoryImpl : TableRepository, JpaRepository<TableEntity, Long> {

    override fun save(entity: Table): Table? {
        val tableEntity: TableEntity = tableMapper(entity).second;
        return tableMapper(save(tableEntity)).second
    }

    override fun findAllImpl(): List<Table?> {
        return findAll().map { tableEntity: TableEntity? -> tableEntity?.let { tableMapper(it).second } }
    }
}
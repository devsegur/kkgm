package me.evertonfs.infra.output

import io.micronaut.data.annotation.Repository
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.domain.repository.TableRepository


@Repository
class TableRepositoryImpl: TableRepository {

    override fun save(entity: TableEntity) {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<TableEntity> {
        TODO("Not yet implemented")
    }
}
package me.evertonfs.infra.output

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.application.mapper.columnWithTableEntityMapper
import me.evertonfs.application.mapper.columnWithoutTableEntityMapper
import me.evertonfs.domain.model.Column
import me.evertonfs.domain.repository.ColumnRepository
import javax.transaction.Transactional

@Repository
abstract class ColumnRepositoryImpl : ColumnRepository, JpaRepository<ColumnEntity, Long> {
    @Transactional(Transactional.TxType.NEVER)
    override fun findAllImpl(): List<Column> {
        return findAll().map { columnEntity -> columnWithoutTableEntityMapper(columnEntity).second }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    override fun save(column: Column): Column {
        return columnWithoutTableEntityMapper(save(columnWithTableEntityMapper(column).second)).second
    }
}
package me.evertonfs.application.service

import me.evertonfs.application.entity.ColumnEntity
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.infra.output.TableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.inject.Singleton

@Service
@Singleton
class TableService(@Autowired val repository: TableRepository) {

    fun listTables(): MutableList<TableEntity> {
        val column = ColumnEntity(null, "coluna" + LocalDateTime.now().nano, null)
        val result = TableEntity(null, "nombre indefinido " + LocalDateTime.now().nano.toString(), listOf(column))
        column.table = result

        repository.save(result)

        return repository.findAll()
    }
}
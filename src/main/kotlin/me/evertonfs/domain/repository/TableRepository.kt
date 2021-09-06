package me.evertonfs.domain.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import me.evertonfs.application.entity.TableEntity
import me.evertonfs.domain.model.Table

interface TableRepository {
    fun save(entity: Table): Table?

    fun findAllImpl(): List<Table?>
}


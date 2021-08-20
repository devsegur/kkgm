package me.evertonfs.infra.output

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import me.evertonfs.application.entity.TableEntity

@Repository
interface TableRepository : JpaRepository<TableEntity, Long>


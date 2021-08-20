package me.evertonfs.application.entity

import me.evertonfs.domain.model.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
@javax.persistence.Table(name = "Tabela")
data class TableEntity(
    @Id @GeneratedValue(strategy = IDENTITY) val id: Long?,
    var name: String,
    @OneToMany var reference: List<ColumnEntity>
) : Table(id, name, reference)
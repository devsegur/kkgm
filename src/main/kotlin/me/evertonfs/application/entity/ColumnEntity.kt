package me.evertonfs.application.entity

import me.evertonfs.domain.model.Column
import javax.persistence.*

@Entity
@Table(name = "Coluna")
data class ColumnEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    var name: String,
    @ManyToOne var table: TableEntity?
) :
    Column(id, name, table) {}
package me.evertonfs.application.entity

import javax.persistence.*

@Entity
@Table(name = "Coluna")
data class ColumnEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @javax.persistence.Column var name: String,
    @ManyToOne var table: TableEntity?
)
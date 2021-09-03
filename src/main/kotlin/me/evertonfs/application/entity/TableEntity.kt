package me.evertonfs.application.entity

import me.evertonfs.domain.model.Column
import me.evertonfs.domain.model.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
@javax.persistence.Table(name = "Tabela")
data class TableEntity(
    @Id @GeneratedValue(strategy = IDENTITY)  var id: Long?,
    @javax.persistence.Column var name: String?,
    @OneToMany
     var reference: List<ColumnEntity>?
)
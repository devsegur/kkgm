package me.evertonfs.application.entity

import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY
import kotlin.jvm.Transient
import kotlin.lazy as lazy1

@Entity
@javax.persistence.Table(name = "Tabela")
open class TableEntity(id: Long?, name: String?, reference: List<ColumnEntity>) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = id

    @javax.persistence.Column
    val name: String? = name

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false)
    var reference: List<ColumnEntity>? = reference

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as TableEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name )"
    }
}
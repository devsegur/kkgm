package me.evertonfs.application.entity

import org.hibernate.Hibernate
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

@Entity
@javax.persistence.Table(name = "Tabela")
open class TableEntity(
    @Id @GeneratedValue(strategy = IDENTITY) open val id: Long?,
    @javax.persistence.Column open val name: String?, reference: List<ColumnEntity>,
) {

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false)
    open var reference: List<ColumnEntity>? = reference

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
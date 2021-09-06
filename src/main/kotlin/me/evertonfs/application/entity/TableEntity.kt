package me.evertonfs.application.entity

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
@javax.persistence.Table(name = "Tabela")
data class TableEntity(
    @Id @GeneratedValue(strategy = IDENTITY) var id: Long?,
    @javax.persistence.Column var name: String?,
    @OneToMany
    var reference: List<ColumnEntity>?
) {
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
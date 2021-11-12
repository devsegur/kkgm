package me.evertonfs.application.entity

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "Coluna")
data class ColumnEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long?,
    @Column var name: String?,
    @ManyToOne var table: TableEntity?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ColumnEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , table = $table )"
    }
}
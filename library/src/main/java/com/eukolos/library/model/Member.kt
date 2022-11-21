package com.eukolos.library.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table
data class Member @JvmOverloads constructor (
        @Id
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
        @Column(length = 36, nullable = false, updatable = false)
        val id: String?= null,
        val email: String,
        val password: String,
        val phone: String,
        val firstName: String,
        val lastName: String,
        @OneToMany
        val borrows: List<Borrow>?= emptyList()
    )

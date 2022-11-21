package com.eukolos.library.model

import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table
data class Borrow @JvmOverloads constructor (
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    val id: String? = "" ,

    @OneToOne
    val book: Book,

    val createdDate: LocalDate = LocalDate.now(),

    val expireDate: LocalDate?=null
    )
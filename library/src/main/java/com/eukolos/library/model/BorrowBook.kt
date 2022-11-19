package com.eukolos.library.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.*

@Entity
data class BorrowBook @JvmOverloads constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: String?= null,
    @OneToOne
    val book: Book,
    @Column(updatable = false)
    @CreationTimestamp
    val createAt: LocalDate,
    val expireAt: LocalDate?=null)
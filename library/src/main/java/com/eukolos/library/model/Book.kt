package com.eukolos.library.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book @JvmOverloads constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: String?=null,
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
    val price: Double,
    val isbn: String,

)
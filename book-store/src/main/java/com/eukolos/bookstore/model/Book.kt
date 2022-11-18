package com.eukolos.bookstore.model

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
@Document
data class Book @JvmOverloads constructor (
    @MongoId(targetType = FieldType.STRING)
    val id: String?,
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
    val price: Double,
    val isbn: String,
    val amount: Int
)
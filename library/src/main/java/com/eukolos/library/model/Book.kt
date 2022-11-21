package com.eukolos.library.model

import com.eukolos.library.dto.ClientBookDto
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table
data class Book @JvmOverloads constructor(
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
 //  @Column(length = 36, nullable = false, updatable = false)
    val id: String? = "" ,
    val title: String,
    val bookYear: Int,
    val author: String,
    val pressName: String,
    val price: Double,
    val isbn: String,
    @JsonIgnore
    @OneToOne(mappedBy = "book")
    val borrow: Borrow?
){
    companion object {
        @JvmStatic
        fun convertFromClient(from: ClientBookDto): Book {
            return Book(
                "",
                from.title,
                from.bookYear,
                from.author,
                from.pressName,
                from.price,
                from.isbn,
                null
            )
        }
    }

}
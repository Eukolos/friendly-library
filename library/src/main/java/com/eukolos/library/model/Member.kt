package com.eukolos.library.model


    data class Member @JvmOverloads constructor (
        val id: String?= null,
        val email: String,
        val password: String,
        val phone: String,
        val firstName: String,
        val lastName: String,
    )

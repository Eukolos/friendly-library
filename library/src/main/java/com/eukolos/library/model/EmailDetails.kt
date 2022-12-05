package com.eukolos.library.model

data class EmailDetails @JvmOverloads constructor(
    val recipient: String,
    val msgBody: String,
    val subject: String,
    val attachment: String,
){}
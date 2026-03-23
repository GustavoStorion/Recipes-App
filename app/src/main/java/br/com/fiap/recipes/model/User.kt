package br.com.fiap.recipes.model

// Implementação da classe de dados User
data class User(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = ""
)

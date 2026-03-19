package br.com.fiap.recipes.navigation

// classe selada porque temos outros objetos que podemos consumir a partir nela
sealed class Destination(val route: String){

    object InitialScreen: Destination(route = "initial")
    object SignupScreen: Destination(route = "signup")
    object HomeScreen: Destination(route = "home")
    object LoginScreen: Destination(route = "login")

}
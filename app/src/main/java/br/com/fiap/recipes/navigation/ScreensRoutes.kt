package br.com.fiap.recipes.navigation

// classe selada porque temos outros objetos que podemos consumir a partir nela
sealed class Destination(val route: String){

    object InitialScreen: Destination(route = "initial")
    object SignupScreen: Destination(route = "signup")

    object HomeScreen: Destination(route = "home/{email}"){
        fun createRoute(email: String): String{
            return "home/$email"
        }
    }
    object LoginScreen: Destination(route = "login")

    object CategoryRecipeScreen: Destination("categoryRecipes/{id}"){
        fun createRoute(id: Int): String{
            return "categoryRecipes/$id"
        }
    }
}
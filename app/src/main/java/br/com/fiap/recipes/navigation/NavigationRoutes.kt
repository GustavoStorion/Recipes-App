package br.com.fiap.recipes.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import br.com.fiap.recipes.screens.HomeScreen
import br.com.fiap.recipes.screens.InitialScreen
import br.com.fiap.recipes.screens.LoginScreen
import br.com.fiap.recipes.screens.SignupScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    //NavHostguarda as nossas rotas
    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen.route
    ){
        composable(Destination.InitialScreen.route){
            InitialScreen(navController)
        }
        composable(Destination.SignupScreen.route) {
            SignupScreen(navController)
        }
        composable(Destination.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(
            route = Destination.HomeScreen.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://recipes.fiap.com.br/{email}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
            }
            )
        ){backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")

            // DESSE JEITO NÃO IMPORTA A ORDEM...
            HomeScreen(
                navController, email
            )
            // DESSE JEITO, VAIR PRECISAR SER IGUAL A ROTA DA FUNÇÃO HomeScreen!!!
            // HomeScreen(navController, email, password, name)

            //pode ser:
            // navController = navController,
            // email= email
        }

    }

}
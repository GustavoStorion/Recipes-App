package br.com.fiap.recipes.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.recipes.R
import br.com.fiap.recipes.components.CategoryItem
import br.com.fiap.recipes.components.RecipeItem
import br.com.fiap.recipes.repository.getAlCategories
import br.com.fiap.recipes.repository.getAllRecipes
import br.com.fiap.recipes.ui.theme.RecipesTheme

@Composable
fun HomeScreen(navController: NavController, email: String?) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {MyTopAppBar(email!!)},  //double bang (tiro de revolver com duas exclamações em desenho animado)
            bottomBar = {MyBottomAppBar()},
            floatingActionButton = {FloatingActionButton()}
            // Espaçamento seguro, o SCAFFOLD sabe onde é a sua área de conteúdo. Então ele vai garantir com que aquilo que você jogar dentro do content, fique dentro do content
            // Necessário passar padddigValues como valor para o parâmetro padding.
        )  { paddingValues ->
            ContentScreen(modifier = Modifier.padding(paddingValues)) // Content area
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RecipesTheme {
        HomeScreen(rememberNavController(), "",) // ou null
    }
}
// Função experimental, sabendo que ela pode mudar...
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(email: String) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
            // .height(60.dp),
        title = {
            Row(
              modifier = Modifier
                  .padding(4.dp)
                  .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
                ) {
                Column {
                    Text(
                        text = "Hello, João",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = email,
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                Card(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    colors = CardDefaults
                        .cardColors(
                            containerColor = Color.Transparent
                        ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                ){
                    Image(
                        painter = painterResource(R.drawable.user),
                        contentDescription = "User image"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun MyTopAppBarPreview() {
    RecipesTheme {
        MyTopAppBar("")
    }
}

// CLASSE que usamos que fornece atributos da classe, contrutores e metodos de acesso
data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun MyBottomAppBar(modifier: Modifier = Modifier){
    val items = listOf(
        BottomNavigationItem(title = "Home", icon = Icons.Default.Home),
        BottomNavigationItem(title = "Favorites", icon = Icons.Default.Favorite),
        BottomNavigationItem(title = "Profile", icon = Icons.Default.Person)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.tertiary
    ){
        items.forEach {item ->
            NavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun MyBottomAppBarPreview() {
    RecipesTheme {
        MyBottomAppBar()
    }
}

@Composable
fun FloatingActionButton(modifier: Modifier = Modifier){
    FloatingActionButton(
        onClick = {},
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add button"
        )
    }
}

@Preview
@Composable
private fun FloatingActionButtonPreview(){
    RecipesTheme {
        FloatingActionButton()
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier) {
    val categories = getAlCategories()
    val recipes = getAllRecipes()

    //Column vai receber o "modifier" com M minúsculo.
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    unfocusedBorderColor = Color.Transparent,
                    unfocusedContainerColor = Color(0xfff5f5f5)
                ),
            label = {
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(R.string.search_by_recipes)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            },
            // Coment to do
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        //Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .fillMaxWidth()
                .height(116.dp)
        ) {
        Image(
            painter = painterResource(R.drawable.cooking_card),
            contentDescription = "Cooking",
            modifier = Modifier
                .fillMaxWidth(),
            // Escala de tamanho do conteudo - Crop aproxima a imagem, cortando-a, mas deixando preencher o card!
            contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "Categories",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        //Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // chamar items list<T>
            items(categories) {category ->
                CategoryItem(category)
            }
        }
        //Spacer(modifier = Modifier.height(116.dp))
        Text(
            text = "Newly added recipes",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(
                vertical = 16.dp,
                horizontal = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recipes) { recipe ->
                RecipeItem(recipe)
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ContentScreenPreview() {
    RecipesTheme {
        ContentScreen()
    }
}










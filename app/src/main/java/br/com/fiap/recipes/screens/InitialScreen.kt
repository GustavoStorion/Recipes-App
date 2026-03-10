package br.com.fiap.recipes.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.recipes.R
import br.com.fiap.recipes.ui.theme.RecipesTheme
import br.com.fiap.recipes.ui.theme.poppinsFamily


@Composable
fun InitialScreen (){
    Box(
        modifier = Modifier // Parametro poderoso em praticamene todos os componentes
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            )

    ){
        //Decoration Components
        TopEndCard(modifier = Modifier.align(Alignment.TopEnd))

        BottomStartCard(modifier = Modifier.align(Alignment.BottomStart))

        Column (
            modifier = Modifier //Propriedade "Modifier" + Parâmetros
                .padding(16.dp) // Necessário fazer a importação da classe ".dp"
                .fillMaxWidth()
                .align(alignment = Alignment.Center), // funciona apenas  para os composes que estão dentro da  box
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image( // Componente IMAGE  UTILIZEI STICKER DO FLATICON
                painter = painterResource(R.drawable.cooking), // painter(parâmetro) para painterResource(Recurso d painter). R -> Resource...drawable...cooking
                contentDescription = "Imagem de uma mulher cozinhando", // Recurso de acessibilidade(descreve a imagem)
                modifier = Modifier
                    .size(190.dp)

                // modifier = Modifier
                // .align(alignment = Alignment.TopCenter) // Método align com parâmetro alignment com valor Alignment.Center.
                // O parâmetro align só funciona como parâmetro para Box
                // Temos outros tipos de Alignment: BottomStart; BottomCenter; BottomEnd; TopStart; TopCenter; TopEnd etc
            )
            Spacer(modifier = Modifier.height(100.dp)) // componente de espaçamento
            Column ( // COMPONÍVEL - UM ABAIXO DO OUTRO
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text( //COMPONÍVEL
                    text = stringResource(R.string.unlimited_recipes), // Parâmetro texto
                    //fontSize = 16.sp, // utilizamos "sp" para fontes (PIXEL ESCALAVEL: pequeno, médio e grande)
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = stringResource(R.string.app_title),
                    fontSize = 64.sp,
                    lineHeight = 64.sp,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                )

                Row {
                    Button(
                        onClick = {}, // propriedade
                        colors = ButtonDefaults //propriedade
                            .buttonColors(  // componente buttonColors
                                containerColor = MaterialTheme.colorScheme.primary
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)  // 48 é a altura minima para objetos clicáveis
                    ) {
                        Text(
                            text = stringResource(R.string.button_login),
                            color = MaterialTheme.colorScheme.onPrimary, // Os dois primeiros Hexa chamamos de alfa e usamos o 0x antes para indicar que vamos usar um valor hexadecimal
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier
                            .width(128.dp)
                            .height(48.dp)
                    )
                    {
                        Text(
                            text = stringResource(R.string.button_login),
                            color = MaterialTheme.colorScheme.onTertiary,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }

    }
}

// FUNÇÃO PREVIEW - Ela chama a func  InicialScreen
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO // Modo da interface do usuário
)
fun InitialScreenPreview(){ // Add preview como boa pratica
    RecipesTheme {
        InitialScreen()
    }
}



package com.example.ep1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ep1.ui.theme.EP1Theme
import com.example.ep1.ui.theme.Raleway
import com.example.ep1.ui.theme.topBar
import com.example.ep1.ui.theme.topLetter
class ChooseActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EP1Theme {
                Column(modifier = Modifier.fillMaxSize()) {
                    // Language Selection Screen
                    LanguageSelectionScreen2()

                    Spacer(modifier = Modifier.height(32.dp)) // Espacio entre LanguageSelectionScreen y FromToButton

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(), // Ocupa todo el espacio disponible
                        contentAlignment = Alignment.Center // Centra el contenido dentro del Box
                    ) {
                        FromToButton2()
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // Espacio entre FromToButton y LazyVerticalGrid

                    // LazyVerticalGrid para mostrar los idiomas
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier
                            .weight(1f) // Utiliza el peso para que ocupe el espacio restante
                            .padding(horizontal = 16.dp) // Añadir un poco de padding
                    ) {
                        items(languages2.size) { index ->
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f)
                                        .border(
                                            width = 1.5.dp,
                                            color = Color.Gray,
                                            shape = RoundedCornerShape(20.dp)
                                        )
                                        .clickable {
                                            mostrarVentana(index)
                                        }
                                ) {
                                    Row (
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ){
                                        IconLanguages2(
                                            imageId = languages2[index].imageId,
                                            name = languages2[index].name
                                        )
                                    }
                                }
                            }
                        } // items
                    } // LazyVerticalGrid
                } // Column
            } // EP1Theme
        }
    }

    private fun mostrarVentana(index: Int) {
        Log.d("PRUEBA", "Se ha seleccionado la opción $index")
        when (index) {
            0 -> startActivity(Intent(this, TranslationActivity::class.java))
            1 -> startActivity(Intent(this, TranslationActivity::class.java))
            2 -> startActivity(Intent(this, TranslationActivity::class.java))
            3 -> startActivity(Intent(this, TranslationActivity::class.java))
            4 -> startActivity(Intent(this, TranslationActivity::class.java))
            else -> finish()
        }
    }
}

data class Language2(val name: String, val imageId: Int)
val languages2 = listOf(
    Language("English", R.drawable.reinounido),
    Language("Español", R.drawable.espana),
    Language("French", R.drawable.francia),
    Language("German", R.drawable.alemania),
    Language("Italiano", R.drawable.italia)
)

@Composable
fun IconLanguages2(name: String, imageId: Int) {
    Spacer(modifier = Modifier.width(24.dp))
    Image(
        painter = painterResource(id = imageId),
        modifier = Modifier.size(48.dp),
        contentDescription = null
    )
    Spacer(modifier = Modifier.width(32.dp))
    Text(text = name,
            fontFamily = Raleway,
        fontWeight = FontWeight.Medium,)
}
@Composable
fun FromToButton2() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(32.dp))
            .fillMaxWidth(0.7f) // Reduce el ancho del contenido a 70% del Row
            .height(86.dp), // Altura fija para permitir el centrado vertical
        verticalAlignment = Alignment.CenterVertically // Centra el contenido verticalmente
    ) {
        // Botón "FROM"
        Box(
            modifier = Modifier
                .background(Color.LightGray, shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(1f) // Ocupa la mitad del espacio disponible
                .fillMaxHeight() // Ocupa toda la altura disponible en el Row
        ) {
            Text(
                text = "FROM:",
                color = Color.Black,
                fontFamily = Raleway,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center) // Centra el Text verticalmente dentro del Box
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        // Botón "TO"
        Box(
            modifier = Modifier
                .background(Color(0xFF3F51B5), shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(1f) // Ocupa la mitad del espacio disponible
                .fillMaxHeight() // Ocupa toda la altura disponible en el Row
        ) {
            Text(
                text = "TO:",
                color = Color.White,
                fontFamily = Raleway,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center) // Centra el Text verticalmente dentro del Box
            )
        }
    }
}

@Composable
fun LanguageSelectionScreen2() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Color de fondo para ver mejor el contenido
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp) // Aumenta la altura si es necesario
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)) // Bordes redondeados
                .background(Color(0xFF3F51B5)) // Fondo azul
                .padding(16.dp), // Espaciado interno
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { /* Acción del menú */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White) // Ícono blanco
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "CHOOSE LANGUAGE", fontSize = 18.sp, color = Color.White,
                fontFamily = Raleway,
                fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, shape = CircleShape)
            )
        }
    }
}

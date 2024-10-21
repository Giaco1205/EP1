package com.example.ep1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ep1.ui.theme.EP1Theme
import com.example.ep1.ui.theme.Raleway
import com.example.ep1.ui.theme.topBar
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Microphone

class TranslationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EP1Theme {
                TranslationScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TranslationScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        TraslateScreen()

        // Box para mostrar los idiomas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Ocupa todo el espacio disponible
            contentAlignment = Alignment.Center // Centra el contenido dentro del Box
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primera tarjeta (English)
                TranslationCard(
                    flagRes = R.drawable.reinounido, // Imagen de la bandera
                    language = "English",
                    text = "Excuse me, where's the carlton hotel? What bus do I have to take?..."
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Icono de traducción
                Box(
                    modifier = Modifier
                    .size(48.dp) // Ajusta el tamaño del Box según tus necesidades
                    .background(topBar, CircleShape),
                    contentAlignment = Alignment.Center // Centra el ícono dentro del Box
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrowsupdown), // Coloca el ícono que prefieras
                        contentDescription = "Translate",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Segunda tarjeta (Spanish)
                TranslationCard(
                    flagRes = R.drawable.espana, // Imagen de la bandera
                    language = "Spanish",
                    text = "Perdona, ¿dónde está el hotel carlton? ¿Qué bus tengo que coger?..."
                )

                Box( // Icono de microfono
                    modifier = Modifier
                        .size(64.dp) // Ajusta el tamaño del Box según tus necesidades
                        .background(topBar, CircleShape)
                        .combinedClickable(
                            onClick = { /* TODO */ },
                            indication = rememberRipple( // Usa rememberRipple para la indicación visual
                                bounded = false, // Permite que la onda se extienda fuera del límite
                                radius = 32.dp // Ajusta el radio de la onda al tamaño del círculo
                            ),
                            interactionSource = remember { MutableInteractionSource() }
                        ), // Fondo circular azul
                    contentAlignment = Alignment.Center // Centra el ícono dentro del Box
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.Microphone,
                        contentDescription = "Translate",
                        modifier = Modifier.size(32.dp),
                        tint = Color.White // Mantén el color del ícono en blanco
                    )
                }
            }
        }
    }
}

@Composable
fun TranslationCard(flagRes: Int, language: String, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(48.dp))
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight()
                    .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(32.dp))
                    .fillMaxWidth()
        ){
            Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(20.dp))
                .height(32.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
                .align(Alignment.Start)
                .fillMaxWidth(0.4f), // Ocupa el 35% del ancho
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Centrar el contenido de la tarjeta

        ) {
            Image(
                painter = painterResource(id = flagRes),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
                Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = language,
                color = Color.Gray,
                fontFamily = Raleway,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center // Alineación centrada del idioma
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
            Text(modifier = Modifier.fillMaxWidth(0.8f),
                text = text,
                fontFamily = Raleway,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left // Alineación centrada del texto
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TraslateScreen() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Color de fondo para ver mejor el contenido
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp) // Aumenta la altura si es necesario
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ) // Bordes redondeados
                    .background(Color(0xFF3F51B5)) // Fondo azul
                    .padding(16.dp), // Espaciado interno
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { /* Acción del menú */ }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    ) // Ícono blanco
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "TEXT TRANSLATE", fontSize = 18.sp, color = Color.White,
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
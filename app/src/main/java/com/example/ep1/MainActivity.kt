package com.example.ep1

import android.content.Intent
import android.graphics.fonts.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ep1.ui.theme.EP1Theme
import com.example.ep1.ui.theme.Raleway

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EP1Theme {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.book_girl),
                        contentDescription = "Girl with Book",
                        colorFilter = ColorFilter.tint(
                            Color(0xFF0a0952),
                            blendMode = BlendMode.Lighten
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RectangleShape)
                    )
                    Box() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomEnd) // Alinea la columna en la parte inferior del Box.
                                .padding(16.dp), // Añade un padding para que no esté completamente pegado a los bordes.
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Box(
                                contentAlignment = Alignment.Center // Centra el contenido del Box
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally // Centra el texto horizontalmente
                                ) {
                                    Text(
                                        text = stringResource(R.string.greeting),
                                        fontSize = 64.sp,
                                        color = Color.White,
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Black,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(8.dp),
                                        lineHeight = 46.sp
                                    )

                                    Text(
                                        text = stringResource(R.string.sub_greeting),
                                        color = Color.White,
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(4.dp).fillMaxWidth(0.5f)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Button(
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@MainActivity,
                                            ChooseActivity::class.java
                                        )
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(Color.White),
                                shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas aquí
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "Empezar", color = Color.Black, fontSize = 16.sp,
                                    fontFamily = Raleway,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .padding(horizontal = 48.dp, vertical = 6.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {

                                },
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                border = BorderStroke(2.dp, Color.White),
                                shape = RoundedCornerShape(16.dp), // Ajusta el radio de las esquinas aquí
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Text(
                                    text = "Registrarse", color = Color.White, fontSize = 16.sp,
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier
                                        .padding(horizontal = 48.dp, vertical = 6.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(86.dp))
                        }
                    }
                }
            }
        }
    }
}
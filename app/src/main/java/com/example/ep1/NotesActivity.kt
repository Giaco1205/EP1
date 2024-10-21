package com.example.ep1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ep1.ui.theme.EP1Theme
import org.json.JSONArray

class NotesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        readService()
    }
    private fun readService(){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.11.14:8080/apiEP2/api.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response -> //response que contiene
                Log.d("VOLLEY", "Response: $response")
                fillArray(response)
            },
            { error ->
                Log.e("VOLLEY", "Error: ${error.message}") // Imprime el error
            })
        queue.add(stringRequest)
    }

    private fun fillArray(response: String) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String, String>>()

        for (i in 0 until jsonArray.length()) {
            val id = jsonArray.getJSONObject(i).getString("id")
            val titulo = jsonArray.getJSONObject(i).getString("titulo")
            val contenido = jsonArray.getJSONObject(i).getString("contenido")
            val fecha = jsonArray.getJSONObject(i).getString("fecha")
            val etiqueta = jsonArray.getJSONObject(i).getString("etiqueta")

            val hashmap = HashMap<String, String>()
            hashmap["id"] = id
            hashmap["titulo"] = titulo
            hashmap["contenido"] = contenido
            hashmap["fecha"] = fecha
            hashmap["etiqueta"] = etiqueta

            arrayList.add(hashmap)
        }
        // Ahora que los datos están en arrayList, puedes ordenarlos si es necesario
        arrayList.sortBy { it["id"]?.toIntOrNull() }
        drawNotes(arrayList)
    }


    @OptIn(ExperimentalMaterial3Api::class)
    private fun drawNotes(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            EP1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        NotesScreen()
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color(0xFF3F51B5),
                                titleContentColor = Color.White,
                            ),
                            title = {
                                Text("Nota")
                            },
                            navigationIcon = {
                                IconButton(onClick = {finish()}) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack, null,
                                        tint = Color.White)
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            //startActivity(Intent(this, ::class.java))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        }
                    },
                ) { innerPadding ->
                    Column (Modifier.padding(innerPadding)){
                        LazyVerticalGrid (
                            columns = GridCells.Fixed(1),
                            modifier = Modifier
                                .weight(1f) // Utiliza el peso para que ocupe el espacio restante
                                .padding(horizontal = 16.dp) // Añadir un poco de padding
                        ){
                            items(items = arrayList) { notes ->
                                Box(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(0.9f)
                                            .clickable {
                                                //mostrarNota(notes)
                                            }) {
                                        DrawNoteItem(notes)
                                    }
                                }
                            }
                        }//LazyColumn
                    }//Column
                }
            }
        }
    }
    /*private fun editNote(category: HashMap<String, String>) {
        val bundle = Bundle().apply { //Empaqueta los datos que se enviarán
            //a un nuevo activity
            putString("idcategoria", category["idcategoria"])
            putString("nombre", category["nombre"])
            putString("description", category["description"])
        }
        val intent = Intent(this, ProductsActivity::class.java)
        intent.putExtras(bundle) //Asi se envian los datos encapsulados en la clase
        startActivity(intent)
    }*/
}

@Composable
fun DrawNoteItem(notes: HashMap<String, String>) {
    Box(
        modifier = Modifier
,        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(180.dp)
                .border(
                    width = 1.5.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(20.dp)
                )
                .align(Alignment.TopEnd)
                .padding(16.dp) // Agregar espaciado interno
        ) {
            // Título en la parte superior
            Text(
                text = notes["titulo"].toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan,
                modifier = Modifier.padding(bottom = 8.dp) // Espaciado inferior
            )
            // Contenido en el centro
            Text(
                text = notes["contenido"].toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp) // Espaciado inferior
            )
            Spacer(modifier = Modifier.height(5.dp))
            // Fila para la fecha y la etiqueta
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Espacio entre fecha y etiqueta
            ) {
                // Fecha a la izquierda
                Text(
                    text = notes["fecha"].toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                // Etiqueta a la derecha
                Box(
                    modifier = Modifier
                        .size(100.dp) // Ajusta el tamaño del Box según tus necesidades
                        .background(Color(0xFFb5b5b5), CircleShape),
                    contentAlignment = Alignment.Center // Centra el ícono dentro del Box
                ) {
                    Text(
                        text = notes["etiqueta"].toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun NotesScreen() {
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

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

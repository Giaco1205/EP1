package com.example.ep1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ep1.ui.theme.EP1Theme
import org.json.JSONArray

class PaisesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        readService()
        enableEdgeToEdge()

    }

    private fun readService() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/paises.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("VOLLEY", response)
                fillCountryArray(response)
            },
            { })
        queue.add(stringRequest)
    }

    private fun fillCountryArray(response: String) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String, String>>()
        for (i in 0 until jsonArray.length()) {
            val idpais = jsonArray.getJSONObject(i).getString("idpais")
            val codpais = jsonArray.getJSONObject(i).getString("codpais")
            val pais = jsonArray.getJSONObject(i).getString("pais")
            val capital = jsonArray.getJSONObject(i).getString("capital")
            val area = jsonArray.getJSONObject(i).getString("area")
            val poblacion = jsonArray.getJSONObject(i).getString("poblacion")
            val continente = jsonArray.getJSONObject(i).getString("continente")

            val hashMap = HashMap<String, String>().apply {
                put("idpais", idpais)
                put("codpais", codpais)
                put("pais", pais)
                put("capital", capital)
                put("area", area)
                put("poblacion", poblacion)
                put("continente", continente)
            }
            arrayList.add(hashMap)
        }
        drawCountries(arrayList)
    }

    private fun drawCountries(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            EP1Theme {
                Column {
                    LazyColumn {
                        items(items = arrayList) { country ->
                            Box(modifier = Modifier.clickable {
                            }) {
                                DrawCountryItem(country)
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun DrawCountryItem(country: HashMap<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 1.dp, color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(16.dp)
    ) {
        Text(text = country["idpais"].toString(), style = MaterialTheme.typography.titleLarge)
        Text(text = country["pais"].toString(), style = MaterialTheme.typography.titleMedium)
        Text(text = "Capital: ${country["capital"]}")
        Text(text = "Área: ${country["area"]} km²")
        Text(text = "Población: ${country["poblacion"]}")
        Text(text = "Continente: ${country["continente"]}")
    }
}
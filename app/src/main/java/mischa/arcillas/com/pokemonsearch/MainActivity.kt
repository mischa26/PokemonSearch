package mischa.arcillas.com.pokemonsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "https://pokeapi.co/api/v2/pokemon/"
    private val Key_Name = "name"
    private val Key_Sprites = "sprites"
    private val Key_FrontDefault = "front_default"
    private val PokeList = ArrayList<PokemonData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.INVISIBLE

        btnSearch.setOnClickListener {
            PokeSearch()
            progressBar.visibility = View.VISIBLE
            PokeList.clear()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun PokeSearch() {
        doAsync {
            val pokeSearched = editText.text.toString()
            val resultJson = URL(url + pokeSearched).readText()
            val jsonObj = JSONObject(resultJson)
            val name = jsonObj.getString(Key_Name)
            val sprites = jsonObj.getJSONObject(Key_Sprites).getString(Key_FrontDefault)
            val weight = jsonObj.getString("weight")
            val height = jsonObj.getString("height")

            val type = jsonObj.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")

            val hp = jsonObj.getJSONArray("stats").getJSONObject(5).getString("base_stat")
            val spDefense = jsonObj.getJSONArray("stats").getJSONObject(1).getString("base_stat")
            val spAttack = jsonObj.getJSONArray("stats").getJSONObject(2).getString("base_stat")

            uiThread {
                recyclerView.adapter = MainAdapter(PokeList)
                PokeList.add(PokemonData(name, Sprite(sprites), weight, height, type, hp,
                        spDefense, spAttack))
                txtPokeName.text = name.substring(0, 1).toUpperCase() + name.substring(1)
                Picasso.with(this@MainActivity).load(sprites).into(imgPoke)
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}

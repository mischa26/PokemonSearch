package mischa.arcillas.com.pokemonsearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pokemon_stat.view.*

/**
 * Created by Mischa on 20/03/2018.
 */
class MainAdapter (val pokemon: ArrayList<PokemonData>): RecyclerView.Adapter<CustomViewHolder> () {

    override fun getItemCount(): Int {
        return pokemon.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.pokemon_stat, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        holder?.view?.txtHeight?.text = pokemon[position].pokeHeight
        holder?.view?.txtWeight?.text = pokemon[position].pokeWeight
        holder?.view?.txtType?.text = pokemon[position].pokeType
        holder?.view?.txtHP?.text = pokemon[position].baseStatHp
        holder?.view?.txtSpDefense?.text = pokemon[position].baseStatSpDefense
        holder?.view?.txtSpAttack?.text = pokemon[position].baseStatSpAttack
    }
}

    class CustomViewHolder (var view: View): RecyclerView.ViewHolder(view){

}



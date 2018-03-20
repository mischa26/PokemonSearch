package mischa.arcillas.com.pokemonsearch

/**
 * Created by Mischa on 20/03/2018.
 */
data class PokemonData (
        var pokeName: String,
        var sprite: Sprite,
        var pokeWeight: String,
        var pokeHeight: String,
        var baseStatHp: String,
        var baseStatSpDefense: String,
        var baseStatSpAttack: String,
        var pokeType: String
)

data class Sprite(val pokeString: String)
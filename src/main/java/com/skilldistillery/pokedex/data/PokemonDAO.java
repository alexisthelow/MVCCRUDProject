package com.skilldistillery.pokedex.data;

import java.util.List;

public interface PokemonDAO {
	
	//create (pokedex)
	Pokemon addPokemon(Pokemon p);
	
	//read (pokedex)
	Pokemon getPokemonById(int id);
	List<Pokemon> getAllPokemon();
	List<Pokemon> getPokemonByType(Type type);	
	
	//update (pokedex)
	Pokemon updatePokemonInPokedex(Pokemon p);
	
	//delete (pokedex)
	Pokemon deletePokemonFromPokedex(Pokemon p);
	
	
	//create (team)
	Pokemon addPokemonToTeam(Pokemon p);
	
	//read (team)
	List<Pokemon> getAllPokemonOnTeam();
	
	//update (team)
	Pokemon updatePokemonOnTeam(Pokemon p);
	
	//delete (team)
	Pokemon removePokemonFromTeam(Pokemon p);



}

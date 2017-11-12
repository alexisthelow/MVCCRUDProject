package com.skilldistillery.pokedex.data;

import java.util.List;

public interface PokemonDAO {
	
	//create (pokedex)
	Pokemon addPokemon(Pokemon p);
	
	//read (pokedex)
	Pokemon getPokemonById(int id);
	Pokemon getPokemonByName(String name);
	List<Pokemon> getAllPokemon();
	List<Pokemon> getPokemonBySingleType(String type);	
	List<Pokemon> getPokemonByBothTypes(String type1, String type2);
	List<Pokemon> getPokemonByEvolutionChainID(int id);
	List<String> getTypeList();
	
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

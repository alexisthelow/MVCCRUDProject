package com.skilldistillery.pokedex.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skilldistillery.pokedex.data.Pokemon;
import com.skilldistillery.pokedex.data.PokemonDAO;

@Controller
@SessionAttributes({"userTeam", "activePokemon", "activeList"})
public class PokemonController {
	
	@Autowired
	private PokemonDAO dao;

	@ModelAttribute("activePokemon")
	public Pokemon initActivePokemon() {
		return dao.getPokemonById(1);
	}
	
	@ModelAttribute("activePokemon")
	public List<Pokemon> initActiveList() {
		return dao.getAllPokemon();
	}
	
	@ModelAttribute("userTeam")
	public List<Pokemon> initUserTeam() {
		return dao.getAllPokemonOnTeam();
	}
	
	
	
	
	
	
}

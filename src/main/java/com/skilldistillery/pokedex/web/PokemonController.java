package com.skilldistillery.pokedex.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.skilldistillery.pokedex.data.PokemonDAO;

@Controller
@SessionAttributes({"userTeam", "activePokemon", "activeList"})
public class PokemonController {
	
	@Autowired
	private PokemonDAO dao;

	
	
	
	
}

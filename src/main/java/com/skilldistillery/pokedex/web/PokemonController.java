package com.skilldistillery.pokedex.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
	
	@ModelAttribute("activeList")
	public List<Pokemon> initActiveList() {
		return dao.getAllPokemon();
	}
	
	@ModelAttribute("userTeam")
	public List<Pokemon> initUserTeam() {
		return dao.getAllPokemonOnTeam();
	}
	
	@RequestMapping(path = "home.do", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute("activeList") List<Pokemon> activeList, 
			@ModelAttribute("activePokemon") Pokemon activePokemon,
			@ModelAttribute("userTeam") List<Pokemon> userTeam) {
		ModelAndView mv = new ModelAndView("index");
		
		return mv;
	}
	
	@RequestMapping(path = "showOnePokemonDetail.do", method = RequestMethod.GET, params = "id")
	public ModelAndView showOnePokemonDetail(@RequestParam("id") int id,
			@ModelAttribute("activePokemon") Pokemon pkmn,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("activePokemon", dao.getPokemonById(id));
		
		
		return mv;
	}
	
	
	
	
}

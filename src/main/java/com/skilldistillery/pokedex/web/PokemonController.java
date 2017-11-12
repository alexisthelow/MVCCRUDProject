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
@SessionAttributes({"userTeam", "activePokemon", "activeList", "types", "activeListIndex"})
public class PokemonController {
	
	@Autowired
	private PokemonDAO dao;
	
	@ModelAttribute("types")
	public List<String> initTypeList() {
		return dao.getTypeList();
	}
	
	@ModelAttribute("activeListIndex")
	public Integer initActiveListIndex() {
		return 0;
	}

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
			@ModelAttribute("userTeam") List<Pokemon> userTeam,
			@ModelAttribute("types") List<String> types) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("activeList", dao.getAllPokemon());
		mv.addObject("userTeam", userTeam);
		mv.addObject("types", types);
		
		return mv;
	}
	
	@RequestMapping(path = "showDetail.do", method = RequestMethod.GET, params = "id")
	public ModelAndView showDetail(@RequestParam("id") int id,
			@ModelAttribute("activeList") List<Pokemon> activeList,
			@ModelAttribute("activeListIndex") Integer activeListIndex) {
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("activePokemon", dao.getPokemonById(id));
		mv.addObject("activeList", activeList);
		
		return mv;
	}
	
	@RequestMapping(path = "showDetail.do", method = RequestMethod.GET, params = "typeFilter")
	public ModelAndView showDetail(@RequestParam("typeFilter") String typeFilter,
			@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		activeList = dao.getPokemonBySingleType(typeFilter);
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", activeList.get(0));
		mv.addObject("activeListIndex", activeListIndex);
		
		return mv;
	}
	
	@RequestMapping(path="prev.do", method=RequestMethod.GET)
	public ModelAndView previousDetail(@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", activeList.get(activeListIndex - 1));
		mv.addObject("activeListIndex", activeListIndex - 1);
		return mv;
	}
	
	@RequestMapping(path="next.do", method=RequestMethod.GET)
	public ModelAndView nextDetail(@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", activeList.get(activeListIndex + 1));
		mv.addObject("activeListIndex", activeListIndex + 1);
		return mv;
	}
	
	
	
}

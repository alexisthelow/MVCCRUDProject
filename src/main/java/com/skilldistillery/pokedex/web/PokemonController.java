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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.pokedex.data.Pokemon;
import com.skilldistillery.pokedex.data.PokemonDAO;

@Controller
@SessionAttributes({"userTeam", "activePokemon", "nextPokemon", "previousPokemon", "activeList", "types", "activeListIndex"})
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
		return dao.getPokemonById(0);
	}
	
	@ModelAttribute("nextPokemon")
	public Pokemon initNextPokemon() {
		return dao.getPokemonById(0);
	}
	
	@ModelAttribute("previousPokemon")
	public Pokemon initPreviousPokemon() {
		return dao.getPokemonById(0);
	}
	
	@ModelAttribute("activeList")
	public List<Pokemon> initActiveList() {
		return dao.getAllPokemon();
	}
	
	@ModelAttribute("userTeam")
	public List<Pokemon> initUserTeam() {
		return dao.getAllPokemonOnTeam();
	}
	
	/**
	 * Shows index view, resets activeList to full list
	 * @param activeList
	 * @param activePokemon
	 * @param userTeam
	 * @param types
	 * @return
	 */

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
	
	/**
	 * Shows detail view with ID parameter
	 * @param id
	 * @param activeList
	 * @param activeListIndex
	 * @return
	 */
	
	@RequestMapping(path = "showDetail.do", method = RequestMethod.GET, params = "id")
	public ModelAndView showDetail(@RequestParam("id") int id,
			@ModelAttribute("activeList") List<Pokemon> activeList,
			@ModelAttribute("activeListIndex") Integer activeListIndex) {
		ModelAndView mv = new ModelAndView("details");
		mv.addObject("activePokemon", dao.getPokemonById(id));
		mv.addObject("activeList", activeList);
		activeListIndex = id;
		mv.addObject("activeListIndex", activeListIndex);
		if (activeListIndex > 1) {
			mv.addObject("previousPokemon", activeList.get(activeListIndex - 1));
		}
		if (activeListIndex < activeList.size() - 1) {
			mv.addObject("nextPokemon", activeList.get(activeListIndex + 1));
		}
		return mv;
	}
	
	/**
	 * Shows detail view by single type filter, sets active list to list filtered by type
	 * @param typeFilter
	 * @param activeListIndex
	 * @param activeList
	 * @return
	 */
	
	@RequestMapping(path = "showDetail.do", method = RequestMethod.GET, params = "typeFilter")
	public ModelAndView showDetail(@RequestParam("typeFilter") String typeFilter,
			@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		activeList = dao.getPokemonBySingleType(typeFilter);
		mv.addObject("activeList", activeList);
		mv.addObject("previousPokemon", dao.getPokemonById(0));
		mv.addObject("activePokemon", activeList.get(0));
		mv.addObject("nextPokemon", activeList.get(1));
		mv.addObject("activeListIndex", 0);
		
		return mv;
	}
	
	/**
	 * Shows previous pokemon in active list
	 * @param activeListIndex
	 * @param activeList
	 * @return
	 */
	
	@RequestMapping(path="prev.do", method=RequestMethod.GET)
	public ModelAndView previousDetail(@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		activeListIndex = activeListIndex - 1;
		mv.addObject("activeListIndex", activeListIndex);
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", activeList.get(activeListIndex));
		if (activeListIndex > 0) {
			mv.addObject("previousPokemon", activeList.get(activeListIndex - 1));
		}
		else {
			mv.addObject("previousPokemon", dao.getPokemonById(0));
		}
		if (activeListIndex < activeList.size() - 1) {
			mv.addObject("nextPokemon", activeList.get(activeListIndex + 1));
		}
		else {
			mv.addObject("nextPokemon", dao.getPokemonById(0));
		}
		return mv;
	}
	
	/**
	 * Shows next pokemon in active list
	 * @param activeListIndex
	 * @param activeList
	 * @return
	 */
	
	@RequestMapping(path="next.do", method=RequestMethod.GET)
	public ModelAndView nextDetail(@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		activeListIndex = activeListIndex + 1;
		mv.addObject("activeListIndex", activeListIndex);
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", activeList.get(activeListIndex));
		if (activeListIndex > 0) {
			mv.addObject("previousPokemon", activeList.get(activeListIndex - 1));
		}
		else {
			mv.addObject("previousPokemon", dao.getPokemonById(0));
		}
		if (activeListIndex < activeList.size() - 1) {
			mv.addObject("nextPokemon", activeList.get(activeListIndex + 1));
		}
		else {
			mv.addObject("nextPokemon", dao.getPokemonById(0));
		}
		return mv;
	}
	
	/**
	 * Updates pokemon from detail view, returns to detail view with updated pokemon
	 * @param activeListIndex
	 * @param activeList
	 * @param pokemon
	 * @return
	 */
	
	@RequestMapping(path="updatePokemon.do", method=RequestMethod.POST)
	public ModelAndView updatePokemon(@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList,
			Pokemon pokemon) {
		ModelAndView mv = new ModelAndView("details");
		dao.updatePokemonInPokedex(pokemon);
		activeList = dao.getAllPokemon();
		mv.addObject("activeList", activeList);
		mv.addObject("activePokemon", dao.getPokemonById(activeListIndex));
		if (activeListIndex > 0) {
			mv.addObject("previousPokemon", activeList.get(activeListIndex - 1));
		}
		else {
			mv.addObject("previousPokemon", dao.getPokemonById(0));
		}
		if (activeListIndex < activeList.size() - 1) {
			mv.addObject("nextPokemon", activeList.get(activeListIndex + 1));
		}
		else {
			mv.addObject("nextPokemon", dao.getPokemonById(0));
		}
		return mv;
	}
	
	/**
	 * Shows add view with blank model pokemon
	 * @return
	 */
	
	@RequestMapping(path="add.do", method=RequestMethod.GET)
	public ModelAndView addNewPokemonToPokedex() {
		ModelAndView mv = new ModelAndView("add");
		mv.addObject("modelPokemon", new Pokemon());
		return mv;
	}
	
	/**
	 * adds newly created pokemon to pokedex, sends to detail view
	 * @param p
	 * @return
	 */
	
	@RequestMapping(path = "processAdd.do", method = RequestMethod.POST)
	public ModelAndView processAddForm(Pokemon p, RedirectAttributes redir) {
		ModelAndView mv = new ModelAndView("redirect:showAddedDetail.do");
		p = dao.addPokemon(p);
		redir.addFlashAttribute("activePokemon", p);
		return mv;
	}
	
	@RequestMapping(path = "showAddedDetail.do", method = RequestMethod.GET)
	public ModelAndView showAddedDetail(@ModelAttribute("activePokemon") Pokemon activePokemon,
			@ModelAttribute("activeListIndex") Integer activeListIndex,
			@ModelAttribute("activeList") List<Pokemon> activeList) {
		ModelAndView mv = new ModelAndView("details");
		activeList = dao.getAllPokemon();
		mv.addObject("activePokemon", activePokemon);
		mv.addObject("activeList", activeList);
		activeListIndex = activePokemon.getId();
		mv.addObject("activeListIndex", activeListIndex);
		if (activeListIndex > 1) {
			mv.addObject("previousPokemon", activeList.get(activeListIndex - 1));
		}
		if (activeListIndex < activeList.size() - 1) {
			mv.addObject("nextPokemon", activeList.get(activeListIndex + 1));
		}
		
		return mv;
	}
	
}

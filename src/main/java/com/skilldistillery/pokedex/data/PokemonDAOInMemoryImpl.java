package com.skilldistillery.pokedex.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

@Repository
@Primary
public class PokemonDAOInMemoryImpl implements PokemonDAO {
	
	private Map<Integer, Pokemon> pokedex = new HashMap<>();
	private List<Pokemon> userTeam = new ArrayList<>();
	private List<String> types = new ArrayList<>();
	private static final String POKEMON_FILE_NAME = "/WEB-INF/pokemon_master_final.csv";
	private static final String TYPES_FILE_NAME = "/WEB-INF/types.csv";
	private static int currentID = 0;
	
	@Autowired
	WebApplicationContext wac;
	
	@PostConstruct
	public void init() {
		try (InputStream is = wac.getServletContext().getResourceAsStream(POKEMON_FILE_NAME); BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();		//discard first line
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				int id = Integer.parseInt(tokens[0]);
				PokemonDAOInMemoryImpl.currentID = id + 1;	//keep track of id for user adding
				String name = tokens[1];
				String type1 = tokens[2];
				String type2 = tokens[3];
				int evolutionStageInChain = Integer.parseInt(tokens[4]);
				int evolutionChainID = Integer.parseInt(tokens[5]);
				String description = tokens[6];
				pokedex.put(id, new Pokemon(id, name, type1, type2, evolutionStageInChain, evolutionChainID, description));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		try (InputStream is = wac.getServletContext().getResourceAsStream(TYPES_FILE_NAME); BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();		//discard first line
			while ((line = buf.readLine()) != null) {
				types.add(line);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public List<String> getTypeList() {
		return types;
	}

	@Override
	public Pokemon addPokemon(Pokemon p) {
		p.setId(currentID++);
		pokedex.put(p.getId(), p);
		return p;
	}

	@Override
	public Pokemon getPokemonById(int id) {		//either returns a pokemon or null
		return pokedex.get(id);
	}

	@Override
	public Pokemon getPokemonByName(String name) {		//either returns a pokemon or null
		List<Pokemon> tempList = new ArrayList<>(pokedex.values());
		Pokemon returnPokemon = null;
		
		for (Pokemon p : tempList) {
			if (p.getName().equalsIgnoreCase(name)) {
				returnPokemon = p;
				break;
			}
		}
		return returnPokemon;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		List<Pokemon> list = new ArrayList<Pokemon>(pokedex.values());
		Collections.sort(list, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return list;
	}

	@Override
	public List<Pokemon> getPokemonBySingleType(String type) {
		List<Pokemon> returnList = new ArrayList<>();
		for (Pokemon p : pokedex.values()) {
			if (p.getType1().equalsIgnoreCase(type) || p.getType2().equalsIgnoreCase(type)) {
				returnList.add(p);
			}
		}
		Collections.sort(returnList, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return returnList;
	}

	@Override
	public List<Pokemon> getPokemonByBothTypes(String type1, String type2) {
		List<Pokemon> returnList = new ArrayList<>();
		for (Pokemon p : pokedex.values()) {
			if ((p.getType1().equalsIgnoreCase(type1) && p.getType2().equalsIgnoreCase(type2)) || (p.getType1().equalsIgnoreCase(type2) && p.getType2().equalsIgnoreCase(type1))) {
				returnList.add(p);
			}
		}
		Collections.sort(returnList, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return returnList;
	}

	@Override
	public List<Pokemon> getPokemonByEvolutionChainID(int id) {
		List<Pokemon> returnList = new ArrayList<>();
		for (Pokemon p : pokedex.values()) {
			if (p.getEvolutionChainID() == id) {
				returnList.add(p);
			}
		}
		Collections.sort(returnList, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return returnList;
	}

	@Override
	public Pokemon updatePokemonInPokedex(Pokemon p) {
		return pokedex.put(p.getId(), p);
	}

	@Override
	public Pokemon deletePokemonFromPokedex(Pokemon p) {
		return pokedex.remove(p.getId());
	}

	@Override
	public Pokemon addPokemonToTeam(Pokemon p) {
		userTeam.add(p);
		return p;
	}

	@Override
	public List<Pokemon> getAllPokemonOnTeam() {
		return userTeam;
	}

	@Override
	public Pokemon updatePokemonOnTeam(Pokemon p) {
		userTeam.set(userTeam.indexOf(p), p);
		return p;
	}

	@Override
	public Pokemon removePokemonFromTeam(Pokemon p) {
		userTeam.remove(p);
		return p;
	}
	
	
	

}

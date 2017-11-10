package com.skilldistillery.pokedex.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private static final String FILE_NAME = "/WEB-INF/pokemon.csv";
	
	@Autowired
	WebApplicationContext wac;
	
	@PostConstruct
	public void init() {
		try (InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME); BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();		//discard first line
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");

				//TODO implement data retrieval from csv
			
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	
	@Override
	public Pokemon addPokemon(Pokemon p) {
		return null;
	}

	@Override
	public Pokemon getPokemonById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pokemon> getPokemonByType(Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon updatePokemonInPokedex(Pokemon p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon deletePokemonFromPokedex(Pokemon p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon addPokemonToTeam(Pokemon p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pokemon> getAllPokemonOnTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon updatePokemonOnTeam(Pokemon p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon removePokemonFromTeam(Pokemon p) {
		// TODO Auto-generated method stub
		return null;
	}

}

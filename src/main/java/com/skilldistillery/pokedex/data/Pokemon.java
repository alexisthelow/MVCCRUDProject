package com.skilldistillery.pokedex.data;

public class Pokemon {

	private int id;
	private String name;
	private String nickname;
	private Type type1;
	private Type type2;
	private int evolutionStage;
	private Pokemon[] evolutionChain;
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;

	//constructors
	public Pokemon() {}

	public Pokemon(int id, String name, String nickname, Type type1, Type type2, int evolutionStage,
			Pokemon[] evolutionChain, Move move1, Move move2, Move move3, Move move4) {
		super();
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.type1 = type1;
		this.type2 = type2;
		this.evolutionStage = evolutionStage;
		this.evolutionChain = evolutionChain;
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
	}
	
	
	
}

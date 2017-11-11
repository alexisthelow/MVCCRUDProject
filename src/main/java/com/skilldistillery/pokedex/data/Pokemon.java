package com.skilldistillery.pokedex.data;

public class Pokemon {

	private int id;
	private String name;
	private String nickname;
	private String type1;
	private String type2;
	private int evolutionStageInChain;
	private int evolutionChainID;
	private String description;
	private Move move1;
	private Move move2;
	private Move move3;
	private Move move4;

	//constructors
	public Pokemon() {}

	public Pokemon(int id, String name, String type1, String type2, int evolutionStageInChain,
			int evolutionChainID, String description) {
		super();
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.evolutionStageInChain = evolutionStageInChain;
		this.evolutionChainID = evolutionChainID;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public int getEvolutionStageInChain() {
		return evolutionStageInChain;
	}

	public void setEvolutionStageInChain(int evolutionStageInChain) {
		this.evolutionStageInChain = evolutionStageInChain;
	}

	public int getEvolutionChainID() {
		return evolutionChainID;
	}

	public void setEvolutionChainID(int evolutionChainID) {
		this.evolutionChainID = evolutionChainID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Move getMove1() {
		return move1;
	}

	public void setMove1(Move move1) {
		this.move1 = move1;
	}

	public Move getMove2() {
		return move2;
	}

	public void setMove2(Move move2) {
		this.move2 = move2;
	}

	public Move getMove3() {
		return move3;
	}

	public void setMove3(Move move3) {
		this.move3 = move3;
	}

	public Move getMove4() {
		return move4;
	}

	public void setMove4(Move move4) {
		this.move4 = move4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	

}

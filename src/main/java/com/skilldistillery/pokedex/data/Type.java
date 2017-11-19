package com.skilldistillery.pokedex.data;

public enum Type {
	NONE("None"),
	NORMAL("Normal"),
	FIGHTING("Fighting"),
	FLYING("Flying"),
	POISON("Poison"),
	GROUND("Ground"),
	ROCK("Rock"),
	BUG("Bug"),
	GHOST("Ghost"),
	STEEL("Steel"),
	FIRE("Fire"),
	WATER("Water"),
	GRASS("Grass"),
	ELECTRIC("Electric"),
	PSYCHIC("Psychic"),
	ICE("Ice"),
	DRAGON("Dragon"),
	DARK("Dark"),
	FAIRY("Fairy");
	
	private String typeName;

	private Type(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return typeName;
	}
	
	

}

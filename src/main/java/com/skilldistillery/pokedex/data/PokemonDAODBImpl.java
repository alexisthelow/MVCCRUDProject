package com.skilldistillery.pokedex.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Primary
public class PokemonDAODBImpl implements PokemonDAO {
	private static String url = "jdbc:mysql://localhost:3306/pokemondb";
	private String user = "pokedex";
	private String pass = "pokedex";
	

	public PokemonDAODBImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Pokemon addPokemon(Pokemon p) {
		Pokemon pokemonAdded = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO pokemon (name, type1, type2, description) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getType1().getTypeName());
			stmt.setString(3, p.getType2().getTypeName());
			stmt.setString(4, p.getDescription());
			stmt.executeUpdate();
			pokemonAdded = p;

			ResultSet keys = stmt.getGeneratedKeys();
			keys.next();
			int i = keys.getInt(1);
			pokemonAdded.setId(i);

			keys.close();
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pokemonAdded;
	}

	@Override
	public Pokemon getPokemonById(int id) {
		Pokemon searchResult = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			rs.next();
			searchResult = new Pokemon();
			searchResult.setId(rs.getInt(1));
			searchResult.setName(rs.getString(2));
			String type1 = rs.getString(3);
			String type2 = rs.getString(4);
			
			for (Type t : Type.values()) {
				if (t.getTypeName().equalsIgnoreCase(type1)) {
					searchResult.setType1(t);
					break;
				}
			}

			for (Type t : Type.values()) {
				if (t.getTypeName().equalsIgnoreCase(type2)) {
					searchResult.setType2(t);
					break;
				}
			}
			
			searchResult.setDescription(rs.getString(5));
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public Pokemon getPokemonByName(String name) {
		Pokemon searchResult = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, name);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			rs.next();
			searchResult = new Pokemon();
			searchResult.setId(rs.getInt(1));
			searchResult.setName(rs.getString(2));
			String type1 = rs.getString(3);
			String type2 = rs.getString(4);
			
			for (Type t : Type.values()) {
				if (t.getTypeName().equalsIgnoreCase(type1)) {
					searchResult.setType1(t);
					break;
				}
			}

			for (Type t : Type.values()) {
				if (t.getTypeName().equalsIgnoreCase(type2)) {
					searchResult.setType2(t);
					break;
				}
			}
			
			searchResult.setDescription(rs.getString(5));
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		List<Pokemon> searchResult = new ArrayList<>();
		try {
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				
				Pokemon p = new Pokemon();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				String type1 = rs.getString(3);
				String type2 = rs.getString(4);
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(type1)) {
						p.setType1(t);
						break;
					}
				}
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(type2)) {
						p.setType2(t);
						break;
					}
				}
				
				p.setDescription(rs.getString(5));
				searchResult.add(p);

			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(searchResult, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return searchResult;
	}

	@Override
	public List<Pokemon> getPokemonBySingleType(String type) {
		List<Pokemon> searchResult = new ArrayList<>();
		try {
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon WHERE type1 LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "%" + type + "%");
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				
				Pokemon p = new Pokemon();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				String type1 = rs.getString(3);
				String type2 = rs.getString(4);
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(type1)) {
						p.setType1(t);
						break;
					}
				}
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(type2)) {
						p.setType2(t);
						break;
					}
				}
				
				p.setDescription(rs.getString(5));
				searchResult.add(p);
			}
			
			
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(searchResult, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return searchResult;
	}

	@Override
	public List<Pokemon> getPokemonByBothTypes(String type1, String type2) {
		List<Pokemon> searchResult = new ArrayList<>();
		try {
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon WHERE ((type1 LIKE ? AND type2 LIKE ?) OR (type2 LIKE ? AND type1 LIKE ?))";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, "%" + type1 + "%");
			stmt.setString(2, "%" + type2 + "%");
			stmt.setString(3, "%" + type1 + "%");
			stmt.setString(4, "%" + type2 + "%");
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				
				Pokemon p = new Pokemon();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				String pType1 = rs.getString(3);
				String pType2 = rs.getString(4);
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(pType1)) {
						p.setType1(t);
						break;
					}
				}
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(pType2)) {
						p.setType2(t);
						break;
					}
				}
				
				p.setDescription(rs.getString(5));
				searchResult.add(p);
			}
			
			
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(searchResult, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return searchResult;
	}

	@Override
	public List<Pokemon> getPokemonByEvolutionChainID(int id) {
		List<Pokemon> searchResult = new ArrayList<>();
		try {
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "SELECT id, name, type1, type2, description FROM pokemon WHERE evolution_chain_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			
			while(rs.next()) {
				
				Pokemon p = new Pokemon();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				String pType1 = rs.getString(3);
				String pType2 = rs.getString(4);
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(pType1)) {
						p.setType1(t);
						break;
					}
				}
				
				for (Type t : Type.values()) {
					if (t.getTypeName().equalsIgnoreCase(pType2)) {
						p.setType2(t);
						break;
					}
				}
				
				p.setDescription(rs.getString(5));
				searchResult.add(p);
			}
			
			
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(searchResult, (p1, p2) -> p1.getId() < p2.getId() ? -1 : 1);
		return searchResult;
	}

	@Override
	public Pokemon updatePokemonInPokedex(Pokemon p) {
		Pokemon pokemonUpdated = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE pokemon SET name = ?, type1 = ?, type2 = ?, description = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getType1().getTypeName());
			stmt.setString(3, p.getType2().getTypeName());
			stmt.setString(4, p.getDescription());
			stmt.setInt(5, p.getId());
			stmt.executeUpdate();
			pokemonUpdated = p;
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pokemonUpdated;
	}

	@Override
	public Pokemon deletePokemonFromPokedex(Pokemon p) {
		Pokemon pokemonDeleted = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM pokemon WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
			pokemonDeleted = p;
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pokemonDeleted;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.skilldistillery.pokedex.data.Pokemon;
import com.skilldistillery.pokedex.data.PokemonDAO;
import com.skilldistillery.pokedex.data.PokemonDAOInMemoryImpl;

public class PokemonDAOInMemoryImplTests {
	
	PokemonDAOInMemoryImpl p;

	@Before
	public void setUp() throws Exception {
		p = new PokemonDAOInMemoryImpl();
	}

	@After
	public void tearDown() throws Exception {
		p = null;
	}

	@Test
	public void testGetAllPokemonReturnsCorrectList() {
		p.init();
		List<Pokemon> list = p.getAllPokemon();
		assertNotNull(list);
		assertEquals("bulbasaur", list.get(0).getName());
		
	}

}

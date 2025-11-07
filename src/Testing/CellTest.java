package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Model.Cell;

class CellTest {

	@Test
	void TestDefecte() {
		Cell c = new Cell();
		assertEquals(0, c.getIsMarked());
		assertEquals(0, c.getRow());
		assertEquals(0, c.getSurroundingMines());
		assertFalse(c.getIsMarked());
		assertFalse(c.getIsMine());
		assertFalse(c.getIsRevealed());
	}
	
	@Test
	void TestParametres() {
		Cell c = new Cell(2,3);
		assertEquals(2,c.getRow());
		assertEquals(3,c.getCol());
	}


}

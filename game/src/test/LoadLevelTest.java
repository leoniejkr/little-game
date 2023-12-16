package test;

import utilz.Img;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import level.Level;

/**
 * Testklasse, die überprüft ob Bilder richtig geladen + korrekt in Level abgespeichert werden.
 * @author leonie & hannah
 *
 */
public class LoadLevelTest {

	
	private String LEVEL1 = "/test/level1.png";
	private String LEVEL1_V2 = "/test/level1(copy).png";
	private final String FLAGGE = "/test/FlaggeTest.png";
	Level level1;
	Level level1copy;
	Level levelflag;
	
	public LoadLevelTest() {
		level1 = new Level(Img.getLevelData(LEVEL1), Img.getLevelFlagData(LEVEL1));
		level1copy = new Level(Img.getLevelData(LEVEL1_V2), Img.getLevelFlagData(LEVEL1_V2));
		levelflag =  new Level(Img.getLevelData(FLAGGE), Img.getLevelFlagData(FLAGGE));
	}
	
	@Test 
	public void testArray() {
		assertEquals (Img.getLevelData(LEVEL1), Img.getLevelData(LEVEL1_V2));
		assertEquals (Img.getLevelFlagData(LEVEL1), Img.getLevelFlagData(LEVEL1_V2));
		assertNotEquals (Img.getLevelData(LEVEL1), Img.getLevelFlagData(LEVEL1_V2));
		assertNotEquals (Img.getLevelData(LEVEL1), Img.getLevelData(FLAGGE));
		
		assertEquals (level1.getData(), level1copy.getData());
		assertEquals (level1.getFlagData(), level1copy.getFlagData());
		assertNotEquals (level1.getData(), level1.getFlagData());
		assertNotEquals (level1.getData(), levelflag.getData()); 

	}
	
}

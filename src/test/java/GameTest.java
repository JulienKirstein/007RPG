package test.java;
import static org.junit.Assert.*;
import org.junit.Test;
import main.java.game.Game;
import java.util.Scanner;
public class GameTest {

	@Test
	public void createGameTest() throws InterruptedException
	{
		Scanner sc = new Scanner("Yannis");
    	Game game = Game.getInstance(sc);
    	assertEquals(game==null,false);
	}
	
	@Test
	public void loadingTest()
	{
		int result = Game.loading();
    	assertEquals(result,1);
	}
	
}

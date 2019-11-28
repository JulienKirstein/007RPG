package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import main.java.game.Game;
import java.util.Scanner;

class GameTest {

    private static  Game game;

    @BeforeClass
    public static void setupBeforeClass()
    {
        game = main.java.game.Game.getInstance();
    }

	@Test
	void setPlayerTest()
	{
		Scanner in = new Scanner("Yannis");
		assertEquals(game.setPlayer(in),"Yannis");
	}
}

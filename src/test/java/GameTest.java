package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import main.java.game.Game;

import java.util.Scanner;

class GameTest {

    public static  Game game;

	@Test
	void createGameTest() throws InterruptedException
	{
		Scanner sc = new Scanner("Yannis");
    	game = Game.getInstance(sc);
    	assertEquals(game==null,false);
	}
}

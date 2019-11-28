package main.java.game;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Game game = Game.getInstance(new Scanner(System.in));
        game.loop();
    }
}

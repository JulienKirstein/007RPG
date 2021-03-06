package main.java.game;

import main.java.game.entity.mob.Mob;
import main.java.game.entity.mob.MobsBoard;
import main.java.game.entity.mob.MobsBuilder;
import main.java.game.entity.player.Player;
import java.util.Scanner;

/*
* Game has a fixed player, level which will increment every 3 fights (fightNumber)
* a score which increase when a mob is killed
* */

public class Game {

    private static Player player;
    private int level = 0;
    private int fightNumber = 0;
    private int score = 0;
    private Mob mob;
    private static Game game = null;
    public static Scanner sc = new Scanner(System.in);

  /*  static {
        try {
            instance = new Game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } */

    private Game(Scanner sc) throws InterruptedException
    {
        System.out.println("WELCOME IN 007 RPG");
        System.out.println("???WHAT IS YOUR NAME HOMIE???");
        setPlayer(sc);
        loading();
    }
    
    private static String setPlayer(Scanner sc)
    {
        String name = sc.nextLine();
        player = Player.getInstance();
        player.setName(name);
        return name;
    }
    /* Setup the Singleton design Pattern
    * */
    public static Game getInstance(Scanner in) throws InterruptedException
    {
    	if (game == null)
    	{
    		game = new Game(in);
    	}
        return game;
    }
    /* upgrade, allows the player to choose between 3 small upgrades every end of level
     * */

    private void upgrade() {

        loading();
        System.out.println("    -----------UPGRADE MENU-----------");
        player.getInfoPlayer();
        boolean ameliorationIsValid = false;
        while (ameliorationIsValid == false) {
            System.out.println("1 -> +10maxHp, 2 -> +5 attack, , 3 -> +3 bullets");
            int action = checkIntInput(sc);
            if (action == 1||action == 2 || action == 3){
                ameliorationIsValid = true;
                if (action == 1){
                    player.setMaxHp(player.getMaxHp()+10);
                }
                if (action == 2){
                    player.setAttack(player.getAttack()+5);
                }
                if (action == 3){
                    player.setAmmo(player.getAmmo()+3);
                }
                loading();

                System.out.println("    ----------SUCCESSFUL UPGRADE-----------");
                System.out.println("    ROUND: " + level);
            }
            else{
                System.out.println("Please enter a valid input");
            }
        }


    }

    private static void wait(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


    public static int loading(){
    	wait(150);
        System.out.println("                    .");
        wait(150);
        System.out.println("                    .");
        wait(150);
        System.out.println("                    .");
        return 1;
    }

    /* fightStatus: gives the Hp of the mob and the player
     * */
    private void fightStatus(){
        System.out.print(player.getName() + " :"+ player.getHp() + "/"+ player.getMaxHp() + "HP");
        System.out.println("                        " + mob.getClass().getSimpleName() + " :"   +  mob.getHp() + "/"+ mob.getMaxHp() + "HP");
    }

    /* fight: starts the fight with a mob
     * */
    private void fight(Mob mob) {

        while(player.getHp() > 0 && mob.getHp() > 0)
        {
            boolean actionIsValid = false;
            while (!actionIsValid) {
                System.out.println("        1 ATTACK, 2 DEFEND, 3 RELOAD");
                int action = checkIntInput(sc);
                if (action == 1 || action == 2 ||action == 3){
                    loading();

                    //1 = attack, 2 = protect , 3 = reload
                    if (action == 1) {// if the player attacks
                        player.setState("Attacking");
                        System.out.print("YOU ATTACK");
                        mobDoAction();
                        player.attack(mob);
                    }

                    if (action == 2) {
                        player.setState("Defending");
                        System.out.print("YOU DEFEND");
                        mobDoAction();
                    }

                    if (action == 3) {
                        player.setState("Reloading");
                        System.out.print("YOU RELOAD");
                        mobDoAction();
                        player.reload();
                    }

                    if (action == 4) {                                                   // for testing purposes
                        mob.setHp(0);
                    }
                    fightStatus();
                    System.out.println("------------------------------------------------------"); // end of one turn
                    actionIsValid = true;
                }
                else{
                    System.out.println("enter valid input please");
                }
            }
        }
    }

    private void mobDoAction(){
        int mobAction = mob.rng(player);

        if (mobAction == 1) {//if both attack they both get hurt
            System.out.println("                        ENEMY ATTACKS");
            mob.attack(player);
        }
        if (mobAction == 2) { //nothing happens and player looses bullets
            System.out.println("                        ENEMY DEFENDS");
        }
        if (mobAction == 3) { //the mob reload and gets attacked
            System.out.println("                        ENEMY RELOADS");
            mob.reload();
        }
    }
	public static int checkIntInput(Scanner sc){
		int i = 0;
		boolean validated = false;
		while(!validated) {
			try { 
				i = Integer.parseInt(sc.nextLine());
				validated = true;
			}
			catch(Exception e) {
				System.out.println("Erreur, veuillez entrez un nombre !");
			}
		}
		return i;
	}
    void displayRules()
    {
        System.out.println("-----------The Rules-----------");
        System.out.println("the rules are simple, you have 3 stats:");
        System.out.println("Health points: when it goes to zero you die");
        System.out.println("Attack: the damage you deal to the enemy when you attack");
        System.out.println("bullets: the number of attack charges that you got");
        System.out.println("");
        System.out.println("each turn you can choose between: ");
        System.out.println("Attacking that will cost you bullets");
        System.out.println("Protect, that will block incoming enemy damages");
        System.out.println("And Reloading that will restore one bullet");
        System.out.println("");
        System.out.println("You and the mobs also got an armor and a weapon");
        System.out.println("The armor allows you to block some damages and regenerates between fights");
        System.out.println("your current armor is " + player.getArmor().getClass().getSimpleName() + " which is not very usefull" );
        System.out.println("");
        System.out.println("The weapon gives you additional damages, self-healing and critical chances which will allow you to hit twice!!");

        System.out.println("Beware : some weapon will cost more bullets per attacks, keep that in mind");
        System.out.println("You also won't be able to see your bullets in the middle of a fight, so keep track of your bullet count!! ");
        System.out.println("your current weapon is " + player.getWeapon().getClass().getSimpleName() + " which is pretty bad" );
        System.out.println("");
        System.out.println("but don't worry you can drop weapons and armor on mobs!" );
        loading();
        System.out.println("Press enter when you are ready" );
        sc.nextLine(); // REPLACE BY sc , avoid recreation of a scanner
        System.out.println("----------------------GOOD LUCK----------------------" );
    }

    public int loop() {
        while (player.getHp() > 0) {
        	if(level==0)
        	{
        		displayRules();
        	}
            MobsBuilder mobsBuilder = new MobsBuilder();
            MobsBoard mobs = mobsBuilder.buildMobs(level);
            player.getInfoPlayer();
            loading();

            while (fightNumber < 3) {    // every 3 fights

                mob = mobs.getMob(fightNumber);      //we pick one of the mobs in the board
                System.out.println("NEW ENEMY : " + mob.getQuality() + " " + mob.getClass().getSimpleName() + " dressed with " +
                        mob.getArmor().getClass().getSimpleName() + " and armed with " + mob.getWeapon().getClass().getSimpleName());
                loading();
                mob.getInfoMob();
                fight(mob);

                if (player.getHp() <= 0) {
                    System.out.println("---------------YOU DIED---------------");
                    System.out.println(score);
                    return 0;
                }
                if (mob.getHp() <= 0) {       //if the mob dies

                    System.out.println(mob.getClass().getSimpleName() + " DEAD");
                    player.regeneration();              //the player regens Hp
                    loading();
                    int drop = (int)(Math.random() * 3);
                    switch(drop) {       //the player has 1/3 chance to drop the enemy weapon and 1/3 chances to drop the enemy armor

                        case 0:
                            System.out.println("???Do you want " + mob.getWeapon().getClass().getSimpleName() + "???");
                            System.out.print("Actual weapon : ");
                            player.getInfoWeapon();
                            System.out.print("New weapon : ");
                            mob.getInfoWeapon();
                            System.out.println("                            y/n");
                            String choice = sc.nextLine();

                            if (choice.equals("y")) {
                                player.setWeapon(mob.getWeapon());
                                System.out.println("you are equipped with : " + player.getWeapon().getClass().getSimpleName());
                                loading();
                            }
                            break;

                        case 1:
                            System.out.println("???Do you want " + mob.getArmor().getClass().getSimpleName() + "???");
                            System.out.print("Actual armor : ");
                            player.getInfoArmor();
                            System.out.print("New armor : ");
                            mob.getInfoArmor();
                            System.out.println("                            y/n");
                            String choiceArm = sc.nextLine();
                            if (choiceArm.equals("y")) {
                                player.setArmor(mob.getArmor());
                                int addPv = mob.getArmor().pdV();
                                player.setMaxHp(player.getMaxHp()+addPv);
                                System.out.println("you are equipped with : " + player.getArmor().getClass().getSimpleName());
                                loading();
                            }
                            break;

                        case 2:
                            break;
                    }
                    player.getInfoPlayer();
                    loading();
                    System.out.println("STILL " + (2 - fightNumber) + " FIGHT BEFORE UPGRADING");
                    fightNumber += 1;    //the player goes to the next fight
                    score += 1;
                    loading();
                }
            }
            level += 1;     //the player goes to the next level, ennemy will be stronger
            fightNumber = 0;
            System.out.println("------------------------------------------------------");
            System.out.println("end of round");
            System.out.println("------------------------------------------------------");
            upgrade();      //the player can upgrade himself
        }
        System.out.println("WELL PLAYED YOU WON");
        return 0;
    }
}
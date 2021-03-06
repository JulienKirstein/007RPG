package main.java.game.entity.player;
import main.java.game.armor.Naked;
import main.java.game.entity.Entity;
import main.java.game.weapon.Fist;
/*
 * Player is an entity that the user controls, his action will be determined by the user
 * he also got an armor and a weapon, as all the entity does
 *
 * */
public class Player extends Entity {

    private static Player instance = new Player();

    /*
    * The player is generated with fixed stats
    * */

    private Player() {
        setHp(100);
        setAttack(10);
        setAmmo(1);
        setMaxHp(100);
        setCritical(0);
        setRegen(2);
        setWeapon(new Fist());
        setArmor(new Naked());
    }

    /*
    * Player is a singleton
    * */
    public static Player getInstance(){

        return instance;
    }

    /* fightStatus: gives the stats and stuff of the player
     * */
    public void getInfoPlayer(){
        System.out.println(getName() + " : "+ getHp() + "/"+ getMaxHp() + "HP     bullets: " +
                getAmmo() + "   attack: "+ getAttack());
        System.out.println("Armed with : " + getWeapon().getClass().getSimpleName());
        System.out.println("Equipped with : " + getArmor().getClass().getSimpleName());
    }
}
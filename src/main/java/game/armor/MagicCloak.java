package main.java.game.armor;
public class MagicCloak implements Armor {
    @Override
    public int damageReduced(){
        return 70;
    }
    @Override
    public int pdV(){
        return 50;
    }
    @Override
    public int regen(){
        return 20;
    }
}

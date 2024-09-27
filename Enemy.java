import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {

    int xPos;
    int yPos;

    String name;
    int level;
    int health;
    int defense;
    int mp;
    int agility;
    int strength;
    int[] statusEffects = new int[4]; //Burn, Freeze, Poison, Bleed
    ArrayList<Spell> spells = new ArrayList<Spell>();
    //TODO more instance vars

    public Enemy(int f, boolean spawn, int x, int y){
        if(!spawn){
            level = f + ThreadLocalRandom.current().nextInt(1, 3);
        } else {
            level = f*2 + 2*ThreadLocalRandom.current().nextInt(1, f);
        }
        setStats();
        xPos = x;
        yPos = y;
    }

    public void setStats(){
        //TODO
    }

    public void action(){
        //enemy action
    }

    public char move(){
        char dir = 'n';
        return dir;
        //moves enemy TODO
    }

    public void enterCombat(Player p, char advantage){
        //balls lmfao
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }
}
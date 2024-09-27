import java.util.concurrent.ThreadLocalRandom;

public class Sword  extends Item{

    double strength; //For physical damage multiplier
    int agility; //For turn order
    int damage; //For base damage
    double criticalChance;
    double criticalDamage;

    public Sword(String n, String desc, String type, boolean un, double str, int agi, int dmg, double cc, double cd, double weight){
        super(n, desc, 0, un, false, true, weight, type);
        strength = str;
        agility = agi;
        damage = dmg;
    }

    @Override
    public double[] getStats(){
        //agi, att, def, hea, mag, man, str
        double[] returner = {agility, damage, 0, 0, 0, 0, strength};
        return returner;
    }
    @Override
    public void randomize() {
        double[] mods = new double[3];
        for(int i = 0; i < 3; i++){
            mods[i] = (double) ThreadLocalRandom.current().nextInt(-10, 41)/100;
        }
        agility +=  (int)(agility * mods[0]);
        damage += (int)(damage *mods[1]);
        strength +=  (strength * mods[2]);
    }

    //DON'T FORGET SCALING
    @Override
    public boolean  merge(Sword swordOne, Sword swordTwo){
        if(swordOne.tier == swordTwo.tier && swordOne.name.equals(swordTwo.name)){
            swordOne.tier ++;
            String temp;
            if(swordOne.tier > 1) {
                temp = swordOne.name.substring(0, swordOne.name.indexOf("["));
            } else {
                temp = swordOne.name + " ";
            }
            temp = temp + "[+" + swordOne.tier + "]";
            swordOne.increaseStats(0.05 * swordOne.tier);
            swordOne.setName(temp);
            return true;
        } else {
            return false;
        }
    }

    private void increaseStats(double amt){
        amt ++;
        strength = strength * amt;
        agility = (int)(agility * amt);
        damage = (int)(damage * amt);
        criticalChance = criticalChance * amt;
        criticalDamage = criticalDamage * amt;
    }

    @Override
    public void printStats() {
        super.printStats();
        if(agility != 0){
            System.out.println("[Agility]: " + agility);
        }
        if(criticalChance != 0){
            System.out.println("[Critical Hit Chance]: " + criticalChance*100 + "%");
        }
        if(criticalDamage != 0){
            System.out.println("[Critical Hit Damage]: " + criticalDamage*100 + "%");
        }
        if(damage != 0){
            System.out.println("[Damage]: " + damage);
        }
        if(strength != 0){
            System.out.println("[Strength Bonus]: " + strength*100 + "%");
        }
        System.out.print("\n");
    }
}

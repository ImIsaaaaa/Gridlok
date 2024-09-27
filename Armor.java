import java.util.concurrent.ThreadLocalRandom;

public class Armor extends Item{

    double defense; //For DR calculation
    int health; //For bonus health when equipped
    int mana; //For bonus max mana when equipped
    double strength; //For physical damage multiplier
    int agility; //For turn order
    double magic; //Spell Damage Multiplier

    public Armor(String n, String desc, String type, int slot, boolean un, double def, int hp, int man, double str, int agi, double mag, double weight){
        super(n, desc, slot, un, false, true, weight, type);
        defense = def;
        health = hp;
        mana = man;
        strength = str;
        agility = agi;
        magic = mag;
    }

    //DON'T FORGET SCALING
    @Override
    public boolean  merge(Armor armorOne, Armor armorTwo){
        if(armorOne.tier == armorTwo.tier && armorOne.name.equals(armorTwo.name)){
            armorOne.tier ++;
            String temp;
            if(armorOne.tier > 1) {
                temp = armorOne.name.substring(0, armorOne.name.indexOf("["));
            } else {
                temp = armorOne.name + " ";
            }
            temp = temp + "[+" + armorOne.tier + "]";
            armorOne.increaseStats(0.05*armorOne.tier);
            armorOne.setName(temp);
            return true;
        } else {
            return false;
        }
    }

@Override
    public double[] getStats(){
        //agi, att, def, hea, mag, man, str
        double[] returner = {agility, 0, defense, health, magic, mana, strength};
        return returner;
    }

    @Override
    public void randomize() {
        double[] mods = new double[6];
        for(int i = 0; i < 6; i++){
            mods[i] = (double) ThreadLocalRandom.current().nextInt(-10, 41)/100;
        }
        agility +=  (int)(agility * mods[0]);
        defense +=  (defense * mods[1]);
        health +=  (int)(health * mods[2]);
        magic +=  (magic * mods[3]);
        mana +=  (int)(mana * mods[4]);
        strength +=  (strength * mods[5]);
    }

    private void increaseStats(double amt){
        amt ++;
        defense = defense*amt;
        health = (int)(health * amt);
        mana = (int)(mana * amt);
        strength = strength * amt;
        agility = (int)(agility * amt);
        magic = magic * amt;
    }

    @Override
    public void printStats() {
        super.printStats();
        if(agility != 0){
            System.out.println("[Agility]: " + agility);
        }
        if(defense != 0){
            System.out.println("[Defense]: " + defense*100 + "%");
        }
        if(health != 0){
            System.out.println("[Health Bonus]: " + health + " Hp");
        }
        if(magic != 0){
            System.out.println("[Magic Damage Bonus]: " + magic*100 + "%");
        }
        if(mana != 0){
            System.out.println("[Mana Increase]: " + mana + " Mp");
        }
        if(strength != 0){
            System.out.println("[Strength]: " + strength*100 + "%");
        }
        System.out.print("\n");
    }
}
import java.util.concurrent.ThreadLocalRandom;

public class Wand  extends Item{

    double defense; //For DR calculation
    int health; //For bonus health when equipped
    int mana; //For bonus max mana when equipped
    int agility; //For turn order
    double magic; //Spell Damage Multiplier
    String damageTypeIncrease; //Increase damage type by 50%

    public Wand(String n, String desc, String type, boolean un, double def, int hp, int ma, int agi, double mag, String dTI, double weight){
        super(n, desc, 0, un, false, true, weight, type);
        defense = def;
        health = hp;
        mana = ma;
        agility = agi;
        magic = mag;
        damageTypeIncrease = dTI;
    }

    @Override
    public double[] getStats(){
        //agi, att, def, hea, mag, man, str
        double[] returner = {agility, 0, defense, health, magic, mana, 0};
        return returner;
    }
    @Override
    public void randomize() {
        double[] mods = new double[5];
        for(int i = 0; i < 5; i++){
            mods[i] = (double) ThreadLocalRandom.current().nextInt(-10, 41)/100;
        }
        agility +=  (int)(agility * mods[0]);
        defense +=  (defense * mods[1]);
        health +=  (int)(health * mods[2]);
        magic +=  (magic * mods[3]);
        mana +=  (int)(mana * mods[4]);
    }

@Override
    public boolean  merge(Wand wandOne, Wand wandTwo){
        if(wandOne.tier == wandTwo.tier && wandOne.name.equals(wandTwo.name)){
            wandOne.tier ++;
            String temp;
            if(wandOne.tier > 1) {
                temp = wandOne.name.substring(0, wandOne.name.indexOf("["));
            } else {
                temp = wandOne.name + " ";
            }
            temp = temp + "[+" + wandOne.tier + "]";
            wandOne.increaseStats(0.05*wandOne.tier);
            wandOne.setName(temp);
            return true;
        } else {
            return false;
        }
    }

    private void increaseStats(double amt){
        defense = defense * (1 + amt);
        health  = (int) (health *  (1+amt));
        mana = (int)(mana * (1+amt));
         agility = (int)(agility * (1+amt));
         magic = magic * (1+amt);
    }

    @Override
    public void printStats() {
        super.printStats();
        if(agility != 0){
            System.out.println("[Agility]: " + agility);
        }
       System.out.println("[Damage Type Increase]: " + damageTypeIncrease);
        if(defense != 0){
            System.out.println("[Defense]: " + defense*100 + "%");
        }
        if(health != 0){
            System.out.println("[Health Increase]: " + health);
        }
        if(magic != 0){
            System.out.println("[Magic Damage Increase]: " + magic*100 + "%");
        }
        if(mana != 0){
            System.out.println("[Mana Bonus]: " + mana);
        }
        System.out.print("\n");
    }
}

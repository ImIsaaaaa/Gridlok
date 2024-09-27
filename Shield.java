import java.util.concurrent.ThreadLocalRandom;

public  class Shield  extends Item{

    double defense; //For DR calculation
    int agility;

    public Shield(String n, String desc, String type, boolean un, double def, int agi, double weight){
        super(n, desc, 5, un, false, true, weight, type);
        defense = def;
        agility = agi;
    }
//don't forget shield of the fallen scaling
    public double getDefense(){
        return defense;
    }

    @Override
    public double[] getStats(){
        //agi, att, def, hea, mag, man, str
        double[] returner = {agility, 0, defense, 0, 0, 0, 0};
        return returner;
    }

    @Override
    public void randomize() {
        double[] mods = new double[2];
        for(int i = 0; i < 2; i++){
            mods[i] = (double) ThreadLocalRandom.current().nextInt(-10, 41)/100;
        }
        agility +=  (int)(agility * mods[0]);
        defense +=  (defense * mods[1]);
    }

    @Override
    public boolean  merge(Shield shieldOne, Shield shieldTwo){
        if(shieldOne.tier == shieldTwo.tier && shieldOne.name .equals(shieldTwo.name)){
            shieldOne.tier ++;
            String temp;
            if(shieldOne.tier > 1) {
                temp = shieldOne.name.substring(0, shieldOne.name.indexOf("["));
            } else {
                temp = shieldOne.name + " ";
            }
            temp = temp + "[+" + shieldOne.tier + "]";
            shieldOne.increaseStats(0.05 * shieldOne.tier);
            shieldOne.setName(temp);
            return true;
        } else {
            return false;
        }
    }

    private  void increaseStats(double amt){
        amt ++;
        defense = defense * amt;
        agility = (int)(agility * amt);
    }

    @Override
    public void printStats() {
        super.printStats();
        if(agility != 0){
            System.out.println("[Agility]: " + agility);
        }
        if(defense != 0) {
            System.out.println("[Defense]: " + defense);
        }
        System.out.print("\n");
    }
}

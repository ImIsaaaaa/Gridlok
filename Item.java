public abstract class Item{
//TODO Add set bonuses at some point maybe
    String name; //For Identity
    String description; //For fun
    int tier; //For stacking and scaling
    boolean consumable; //Is usable (consumable)
    boolean equipable; //Can be equipped
    boolean unique; //For preventing duplicate items
    boolean canObtain; // to prevent dupes
    int slotNumber; //To see where the fuck in the arraylist I put this shit
    double dropWeight; //To change drop chance
    String typeConverter; //To cast to the correct class

    public Item(String n, String desc, int slot, boolean un, boolean cons, boolean eq, double weight, String type){
        name = n;
        description = desc;
        slotNumber = slot;
        canObtain = true;
        unique = un;
        consumable = cons;
        dropWeight = weight;
        tier = 0;
        equipable = eq;
        typeConverter = type;
    }

    public void randomize(){
        //Nothing...
    }

    public void printStats(){
        System.out.print("\n\t--STATS--\n");
    }

    //DON'T FORGET SCALING
    public boolean merge(Item itemOne, Item itemTwo){
        System.out.println("Checkpoint from the wrong class"); //TODO Fix this
        return false;
    }
    public  boolean  merge(Armor armorOne, Armor armorTwo){
        return false;
    }
    public  boolean  merge(Wand wandOne, Wand wandTwo){
        return false;
    }
    public  boolean  merge(Sword swordOne, Sword swordTwo){
        return false;
    }
    public  boolean  merge(Shield shieldOne, Shield shieldTwo){
        return false;
    }

    public double[]  getStats(){
        return null;
    }

    public double getWeight(){
        return dropWeight;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setObtainable(boolean val){
        canObtain = val;
    }

    public String getName(){
        return name;
    }

    public boolean isUnique(){
        return unique;
    }
}
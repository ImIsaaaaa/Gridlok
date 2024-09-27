public class Consumable extends Item{

    int healing; //For health potions
    int manaRegen; //For mana potions
    Spell spell; //For scrolls

    public Consumable(String n, String desc, String type, boolean un, int heal, int mR, Spell sp, double weight){
        super(n, desc, -1, un, true, false,  weight, type);
        healing = heal;
        manaRegen = mR;
        spell = sp;
    }

    public int getHealing(){
        return healing;
    }

    public int getManaRegen(){
        return manaRegen;
    }

    public int[] consume(){
       int[] returner = {healing, manaRegen};
        return returner;
    }

    public Spell getSpell(){
        return spell;
    }

    @Override
    public void printStats() {
        super.printStats();
        if(healing == 0 && manaRegen == 0){
            System.out.println("When consumed, this scroll will infuse you with its magical power, and allow you to wield the spell within.");
        } else if(healing != 0 && manaRegen == 0){
            System.out.println("When consumed, this potion will restore " + healing + " Hp.");
        } else if(healing == 0 && manaRegen != 0){
            System.out.println("When consumed, this potion will restore " + manaRegen + " Mp.");
        }
        System.out.print("\n");
    }
}

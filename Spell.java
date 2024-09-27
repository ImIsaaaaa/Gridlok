import java.util.concurrent.ThreadLocalRandom;

public class Spell{

    int damage;
    int bonusDamage;
    int manaCost;
    String damageType;
    String bonusDamageType;
    String name;

    public Spell(String n, int d, String dT, int bD, String bDT, int mC){
        name = n;
        damage = d;
        damageType = dT;
        bonusDamage = bD;
        bonusDamageType = bDT;
        manaCost = mC;
    }

    //Cast the spell, do the damage, take the mana, apply the status effects, etc. TODO test
    public void cast(Player p, Enemy e, char t){
        if(t == 'e' && p.mana >= manaCost){
            p.mana -= manaCost;
            e.health -= damage;
            e.health -= bonusDamage;
            applyStatusEffects(p, e, t);
        } else if(t == 'p' && e.mp >= manaCost){
            e.mp -= manaCost;
            p.health -= damage;
            p.health -= bonusDamage;
            applyStatusEffects(p, e, t);
        }
        //TODO something if not enough mana, do after combat impliemnted
    }
    //Applies status effects TODO test
    public void applyStatusEffects(Player p, Enemy e, char target){
        int burnStacks = 0;
        int freezeStacks = 0;
        int poisonStacks = 0;
        int bleedStacks = 0;
        if(damageType.equals("Fire")){
            burnStacks++;
        } else if(damageType.equals("Ice")){
            if(ThreadLocalRandom.current().nextInt(0, 5) == 2){
                freezeStacks ++;
            }
        } else if(damageType.equals("Poison")){
            poisonStacks += ThreadLocalRandom.current().nextInt(0, 5);
        } else {
            if(ThreadLocalRandom.current().nextInt(0, 5) == 2){
                bleedStacks ++;
            }
        }
        if(bonusDamageType.equals("Fire")){
            burnStacks++;
        } else if(bonusDamageType.equals("Ice")){
            if(ThreadLocalRandom.current().nextInt(0, 10) == 2){
                freezeStacks ++;
            }
        } else if(bonusDamageType.equals("Poison")){
            poisonStacks += ThreadLocalRandom.current().nextInt(0, 2);
        } else {
            if(ThreadLocalRandom.current().nextInt(0, 20) == 2){
                bleedStacks ++;
            }
        }
        if(target == 'e'){
            e.statusEffects[0] += burnStacks;
            e.statusEffects[1] += freezeStacks;
            e.statusEffects[2] += poisonStacks;
            e.statusEffects[3] += bleedStacks;
        } else {
            p.statusEffects[0] += burnStacks;
            p.statusEffects[1] += freezeStacks;
            p.statusEffects[2] += poisonStacks;
            p.statusEffects[3] += bleedStacks;
        }
    }

    public String getName(){
        return name;
    }

    public int getDamage(){
        return damage;
    }

    public int getBonusDamage(){
        return bonusDamage;
    }

    public int getManaCost(){
        return manaCost;
    }

    public String getDamageType(){
        return damageType;
    }

    public String getBonusDamageType(){
        return bonusDamageType;
    }
}
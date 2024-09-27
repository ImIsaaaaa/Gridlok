import java.util.*;

public class SpellList{

    ArrayList<Spell> spells = new ArrayList<Spell>();

    public SpellList(){
        spells.add(new Spell("Burning Lance", 3, "Fire", 0, "Fire", 1));
        spells.add(new Spell("Freezing Lance", 3, "Ice", 0, "Ice", 2));
        spells.add(new Spell("Fireball", 5, "Fire", 0, "Fire", 2));
        spells.add(new Spell("Rotting Flesh", 3, "Poison", 2, "Poison", 3));
        spells.add(new Spell("Storm of Daggers", 7, "Physical", 3, "Physical", 3));
        spells.add(new Spell("Firefreeze", 3, "Ice", 3, "Fire", 4));
        spells.add(new Spell("Shattering Star", 15, "Fire", 3, "Physical", 6));
        spells.add(new Spell("Plague Lance", 3, "Poison", 0, "Poison", 2));
        spells.add(new Spell("Frozen Lament", 4, "Ice", 3, "Physical", 5));
        //spells.add(new Spell("NAME", dmg, dmgType, bonusDmg, bonusDmgType, manaCost));
    }

}
import java.util.ArrayList;

public class ItemList{
    ArrayList<Item> items = new ArrayList<Item>();
//TODO give each item a UNICODE UTF-8 ICON (like  ⤒ for a staff),
    //TODO balance weight
    //REMEMBER, HIGH WEIGHT = HIGH DROP CHANCE
    public ItemList(){
        SpellList spellListInstance = new SpellList();
        items.add(new Wand("Wand of the Blizzard", "A wand with a freezing aura emanating from it. Cold to the touch.",  "Wand", false, 0, 0, 2, 0, 0.08, "ICE", 0.15));
        items.add(new Wand("Wand of Burning", "A wand with a blazing fire surrounding it. Hot to the touch.", "Wand", false, 0, 0, 2, 0, 0.08, "FIRE",0.15));
        items.add(new Wand("Wand of the Plague", "A wand with a foul stench radiating off of it. Feels almost fleshy in your hand.", "Wand", false, 0, 0, 2, 0, 0.08, "POIS", 0.15));
        items.add(new Wand("Wand of Haste", "A light wand, when you hold it you feel a strong wind within. You feel faster and more agile with it.", "Wand", false, 0, 0, 1, 2, 0.08, "NULL",0.15));
        items.add(new Wand("Wand of Fortitude", "A strong and dense wood makes up this wand. Whenever you take a hit, you feel the wand absorb some of it.", "Wand", false, 0.07, 0, 1, 0, 0.08, "NULL", 0.15));
        items.add(new Wand("Wand of Vitality", "A stone wand with ruby inlays, you feel stronger holding this wand. The warm pulse of the magic within reassures you.", "Wand", false, 0, 2, 1, 0, 0.08, "NULL", 0.15));
        items.add(new Wand("Aftershock", "A wand that feels like it holds the power to shake the core of the planet. Unfortunately, you are only able to harness a fraction of this power.", "Wand", true, 0, 0, 2, 0, 0.1, "BONU", 0.03));
        items.add(new Sword("Rusty longsword", "A long abandoned longsword which hasn't seen action in years. Hopefully it's still sharp enough", "Sword", false, 0.04, 0, 4, 0.05, 1.3, 0.15));
        items.add(new Sword("Claymore", "More of a large hunk of iron than a sword. It's just so big. Hits like a truck.", "Sword",false, 0.05, -2, 6, 0.15, 1.5, 0.15));
        items.add(new Sword("Pair of Daggers", "A small pair of daggers, more akin to throwing knives. You could take advantage of their low weight.", "Sword",false, 0.01, 3, 3, .75, 1.5, 0.15));
        items.add(new Sword("Rapier", "Long and thin, only capable of jabbing, but designed for speed. It will take some practice, but you have more than enough time down here.", "Sword",false, 0.02, 2, 4, .33, 1.3, 0.15));
        items.add(new Sword("Sword of the Fallen", "A sword that has been weilded by many who have now fallen. Scales with floor.", "Sword",true, 0.07, 1, 7, .25, 1.25, 0.02)); //SPECIAL ITEM
        //SCALING: (/floor) +0.05str,
        items.add(new Armor("Sorcerer's Hood", "A hood inscribed with mystical runes, it fits snugly over your head.", "Armor",1, false, 0, 0, 1, 0, 0, 0.03, 0.1));
        items.add(new Armor("Sorcerer's Robe", "A long, flowing, silk robe adorned with various designs.", "Armor",2, false, 0.03, 0, 3, 0, 0, 0.06, 0.1));
        items.add(new Armor("Sorcerer's Belt", "A fine leather belt with magically imbued talismans, it even comes with slots for your Mana potions for convenience","Armor", 3, false, 0.01, 0, 2, 0, 0, 0.04, 0.1));
        items.add(new Armor("Sorcerer's Boots", "Tall leather boots. When you reach inside, you pull out a star-shaped talisman, slotting it neatly into the socket on the front of the boot.", "Armor",4, false, 0.01, 0, 1, 0, 0, 0.03, 0.1));
        items.add(new Armor("Paladin's Helmet", "Once a symbol of the light, the once grand Paladin lies on the ground, a spear in his chest. You decide to salvage his helmet. It's of greater use to you than to him.", "Armor", 1, false, 0.05, 0, 0, 0, 0, 0, 0.1));
        items.add(new Armor("Paladin's Chestplate", "A huge metallic object, its weight encumbers you. No matter though, you feel invincible.", "Armor",2,  false, 0.08, 0, 0, 0.04, -2, 0, 0.1));
        items.add(new Armor("Paladin's Leggings", "Highly uncomfortable, yet highly effective at stopping anything and anyone that may want to share their bad day with your lower body.", "Armor",3, false, 0.06, 0, 0, 1, -1, 0, 0.1));
        items.add(new Armor("Paladin's Boots", "Two boots, nearly identical. It takes you a moment to figure ouch which one is the left and right. You still suspect that they might be both for the same foot.", "Armor",4, false , 0.05, 0, 0, 0, 0, 0, 0.1));
        items.add(new Armor("Rogue's Visor", "A highly futuristic device, it allows you to see any enemies through walls, granting you a slight advantage from being attacked from behind.", "Armor",1, false, 0, 0, 0, 0, 1, 0, 0.1));
        items.add(new Armor("Rogue's Cloak", "A cloak made from sowing shadows together, its dark mantle seemingly shifts with your movements.", "Armor",2, false, 0.03, 0, 0, 0.02, 2, 0, 0.1));
        items.add(new Armor("Rogue's Bandolier", "Fitting perfectly over your chest and clipping to your belt for security, this bandolier comes with pins for smoke bombs, even having a few still attached.", "Armor",3, false, 0.02, 0, 0, 0, 1, 0, 0.1));
        items.add(new Armor("Rogue's Treads", "Hard boots with a soft padding and the inside lined with fur of some exotic animal, they make next to no sound as you traverse the dungeon's many rooms.", "Armor",4, false, 0.01, 0, 0, 0, 1, 0, 0.1));
        items.add(new Armor("Helmet of the Fallen", "A Helmet once worn by a great knight, it is adorned with golden streaks, showing the immense wealth and power this unfortunate soul once head. Scales with floor.", "Armor",1, true, 0.04, 1, 1, 0.04, 1, 0.03, 0.02)); //SPECIAL ITEM
        //SCALING: (/floor)  +0.03def, +0.03mag, +0.03str
        items.add(new Armor("Chestplate of the Fallen", "A chestpiece worn by a prominent military figure, it is adorned with a multitude of distinctions. Scales with floor.", "Armor",2, true, 0.1, 0, 1, 0.07, 0, 0.05, 0.02)); //SPECIAL ITEM
        //SCALING: (/floor)  +0.03def, +0.03mag, +0.03str
        items.add(new Armor("Leggings of the Fallen", "Ripped off of likely royalty and then dragged through the dungeon by the unfortunate theif who lies lifeless in front of you. Now it's your turn to boast them. Scales with floor.", "Armor",3, true, 0.05, 1, 1, 0.04, 0, 0.04, 0.02)); //SPECIAL ITEM
        //SCALING: (/floor)  +0.03def, +0.03mag, +0.03str
        items.add(new Armor("Boots of the Fallen", "Such wonderous boots must have belonged to only the most prestigious craftsman, with the numerous designs inlayed on them with the most precious metals. Would make a great addition to your collection, if you ever get out of here. Scales with floor", "Armor", 4, true, 0.03, 1, 1, 0.03, 1, 0, 0.02)); //SPECIAL ITEM
        //SCALING: (/floor)  +0.03def, +0.03mag, +0.03str
        items.add(new Shield("Sorcerer's Buckler", "A small shield, It's made of a thin, lightweight material, yet it's still strong enough to protect you.", "Shield",false, 0.07, 1, 0.15));
        items.add(new Shield("Paladin's Bulwark", "More a wall than a shield, this bulwark is made of a thick, metallic material, it's strong, and guarantees that nearly anything coming your way won't get to you.", "Shield",false,  0.1, -3,  0.15));
        items.add(new Shield("Rusty Shield", "A circular disc covered in rust, dents, and scratches. With it clearly seeing extensive use, you wonder how much more it can take before it breaks.", "Shield",false, 0.07, 0, 0.15));
        items.add(new Shield("Rogue's Vambrace", "Braces for your forearm, a combination of leather and iron, they should hold up nicely, despite them not being the most effective in area coverage.", "Shield",false, 0.05,  2,0.15));
        items.add(new Shield("Shield of the Fallen", "A great shield, larger than almost any you've seen before, you expect it to weigh more than you can carry, yet it's freakishly light. Scales with floor.", "Shield",true, 0.1, 0,0.02)); //SPECIAL ITEM
        //SCALING: (/floor) +0.05 def
        items.add(new Consumable("Scroll of Fireball", "A scroll containing the power of a fireball spell.", "Cons",true, 0, 0,  spellListInstance.spells.get(2), 0.07));
        items.add(new Consumable("Scroll of Rotting Flesh", "A scroll containing the power of a spell that makes flesh rot.", "Cons",true, 0, 0,  spellListInstance.spells.get(3),  0.07));
        items.add(new Consumable("Scroll of One Thousand Daggers", "A scroll containing the power to summon one thousand tiny daggers.", "Cons",true, 0, 0,  spellListInstance.spells.get(4),  0.07));
        items.add(new Consumable("Scroll of Firefreeze", "A scroll containing the power to both burn and freeze.", "Cons",true, 0, 0,   spellListInstance.spells.get(5), 0.07));
        items.add(new Consumable("Scroll of Shattering Star", "A scroll containing the power of a star, thousands of light-years away.", "Cons",true, 0, 0,  spellListInstance.spells.get(6),  0.07));
        items.add(new Consumable("Scroll of Plague Lance", "A scroll containing the power to summon a diseased lance", "Cons",true, 0, 0,  spellListInstance.spells.get(7),  0.07));
        items.add(new Consumable("Scroll of Frozen Lament", "A scroll containing the power to freeze someone's soul.", "Cons",true, 0, 0,   spellListInstance.spells.get(8),  0.07));
        items.add(new Consumable("Potion of Healing", "A flask of Red liquid. Tastes horrible. Restores 3 HP.", "Cons",false, 3, 0,  null, 0.5));
        items.add(new Consumable("Potion of Mana", "A flask of Blue liquid. Smells musty. Restores 2 MP.","Cons", false, 0, 2,  null,0.5));
    }

    public int getSize(){
        return items.size();
    }

    public Item getIndex(int index){
        return items.get(index);
    }
}
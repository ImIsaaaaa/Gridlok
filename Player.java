import java.util.*;
public class Player{

    int health;
    int maxHealth;
    final int BASE_MAX_HEALTH;
    int mana;
    int maxMana;
    final int BASE_MAX_MANA;
    char direction = 'n';//N,S,E,W
    int attack;
    final int BASE_MAX_ATTACK;
    double strength;
    final double BASE_MAX_STRENGTH ;
    double defense;
    final double BASE_MAX_DEFENSE ;
    int agility; //more math yay
    final int BASE_MAX_AGILITY ;
    double magicDamageMultiplier = 1;
    final double BASE_MAGIC_DAMAGE_MULT = 1;
    int[] statusEffects = {0, 0, 0, 0}; //Burn, Freeze, Poison, Bleed (stacks)
    int page = 0; //Page of inventory
    SpellList spelllist = new SpellList();
    ArrayList<Item> inventory = new ArrayList<Item>();
    ArrayList<Item> loadout = new ArrayList<Item>(); //in order
    String[] header = {" (Hand) ", " (Helmet) ", " (Chestplate) ", " (Leggings) ", " (Boots) ", " (Shield) "};
    //weapon, helmet, chestplate, leggings, boots, shield
    ArrayList<Spell> spellbook = new ArrayList<Spell>();


    public Player(int h, int m, double s){
        health = h;
        maxHealth = h;
        maxMana = m;
        mana = m;
        strength = s;
        defense = 0;
        agility = 10;
        attack = 1;
        BASE_MAX_STRENGTH = s;
        BASE_MAX_AGILITY = 10;
        BASE_MAX_ATTACK = 1;
        BASE_MAX_DEFENSE = 0;
        BASE_MAX_HEALTH = h;
        BASE_MAX_MANA = m;
        spellbook.add(spelllist.spells.get(0));
        spellbook.add(spelllist.spells.get(1));
        fill();
    }

    private void fill(){
        for(int i = 0; i < 6; i++) {
            loadout.add(null);
        }
    }

    public void updateStats(){ //UPDATE ALL STATS
        //TODO make health increase with max health and the remove it if it is less
        agility = BASE_MAX_AGILITY;
        attack = BASE_MAX_ATTACK;
        defense = BASE_MAX_DEFENSE;
        maxHealth = BASE_MAX_HEALTH;
        magicDamageMultiplier = BASE_MAGIC_DAMAGE_MULT;
        maxMana = BASE_MAX_MANA;
        strength = BASE_MAX_STRENGTH;
        for(int i = 0; i < 6; i++){
            if(loadout.get(i) != null) {
                double[] toAdd = loadout.get(i).getStats();
                //agi, att, def, hea, mag, man, str
                agility += (int)toAdd[0];
                attack += (int)toAdd[1];
                defense += toAdd[2];
                maxHealth += (int)toAdd[3];
                magicDamageMultiplier += toAdd[4];
                maxMana += (int)toAdd[5];
                strength += toAdd[6];
            }
        }
    }

    //Moves the player through room class
    public void move(char direction, Room area){
        if(direction == 'w'){
            area.update('n');
        } else if(direction == 's'){
            area.update('s');
        } else if(direction == 'a'){
            area.update('w');
        } else if(direction == 'd'){
            area.update('e');
        } else if (direction == 'x'){
            area.interact('a', this);
        } else if (direction == 'i'){
            Main.chesting = true;
            openInventory(area);
        } else if (direction == 'e'){
            area.interact('e', this);
        }
    }

    //Opens inventory screen where player can view and consume items, 
    //and change equipment
    private void openInventory(Room area){
        do{
            openInvMain();
            int action;
            action = Main.input.nextInt();
            if(action == 5){
                break;
            } else if(action == 2){
                openLoadout(area);
            } else if(action == 3){
                openSpelllist(area);
            } else if(action == 4){
                openStats(area);
            } else if(action == 1){
                openMainInv(area);
            }
        } while (Main.chesting);
        String buffer = "";
        buffer = Main.input.nextLine();
        clear();
        area.printRoom();
        Main.chesting = false;
    }

    private void openInvMain(){
        clear();
        System.out.println("Inventory\n\n");
        System.out.println("\t[1]: View Inventory");
        System.out.println("\t[2]: View Equipment");
        System.out.println("\t[3]: View Spells");
        System.out.println("\t[4]: View Stats");
        System.out.println("\t[5]: Exit\n");
        System.out.print("> ");
    }

    private void openMainInv(Room area){
        clear();
        printInventory(page);
        Main.input.nextLine();
        String ipt;
        char selection ;
        do{
            ipt= Main.input.nextLine();
            selection = ipt.charAt(0);
            if(selection == '0'){
                page = 0;
                break;
            } else {
                int sel;
                if (selection == '+') {
                    page++;
                    clear();
                    printInventory(page);
                } else if (selection == '-' && page > 0) {
                    page--;
                    clear();
                    printInventory(page);
                } else {
                    sel = ((int) selection) - 48;
                    openItem((sel), area);
                }
            }
        }while(Main.chesting);
        clear();
        if(Main.chesting) {
            openInventory(area);
        }
    }

    private void printInventory(int page){
        int len = inventory.size();
        System.out.println("Inventory (Page " + (page+1 )+ " of " + (((int)len/8)+1 ) + ")\n\n");
        for(int i = 0; i < 9;  i++){
            if(i >= (len-page*9)){
                break;
            }
            System.out.println("\t[" + (i+1) + "]: " + inventory.get((page * 9) + i).getName());
        }
        if(page != 0){
            System.out.println("\t[-]: Previous Page" );
        }
        if(len >= (page+1)*9){
            System.out.println("\t[+]: Next Page");
        }
        System.out.print("\t[0]: Back to Inventory\n\n>");
    }

    private void openItem(int sel, Room area){
        clear();
        System.out.println(inventory.get(sel-1).getName());
        boolean consumable = inventory.get(sel-1).consumable;
        System.out.println( inventory.get(sel-1).description);
        inventory.get(sel - 1).printStats();
        if(consumable){
            System.out.println("\n[1]: Consume" );
        } else {
            //Get slot that the item should be equipped in, and then if there is nothing in that slot [1]: Equip, else [1]: Replace 'item'
            int slot = inventory.get(sel-1).slotNumber;
            if(loadout.get(slot) == null){
                System.out.println("\n[1] Equip");
            } else {
                System.out.println("\n[1]: Replace {" + loadout.get(slot).getName() + "}");
            }
        }
        System.out.println("[2]: Drop");
        System.out.print("[0]: Back to Inventory\n\n>");
        int choice = Main.input.nextInt();
        if(choice == 1){
            if(consumable){
                consume((Consumable) inventory.get(sel-1));
                inventory.remove(sel-1);
            }  else {
                equip(inventory.get(sel-1));
                inventory.remove(sel-1);
            }
        } else if (choice == 2){
            inventory.remove(sel-1);
        }
            openMainInv(area);
    }

    private void equip(Item item){
        int slot = item.slotNumber;
        Item temp = loadout.get(slot);
        if(temp != null){
            inventory.add(temp);
        }
        loadout.set(slot, item);
        updateStats();
    }

    private void consume(Consumable item){
       int[] toAdd = item.consume();
       if(toAdd[0] == 0 && toAdd[1] == 0){
           spellbook.add(item.getSpell());
       } else {
           health += toAdd[0];
           if(health > maxHealth){
               health = maxHealth;
           }
           mana += toAdd[1];
           if(mana > maxMana){
               mana = maxMana;
           }
       }
    }

    private void openLoadout(Room area){
        clear();
        printLoadout();
        Main.input.nextLine();
        int selection ;
        do{
            selection = Main.input.nextInt();
            if(selection == 0){
                break;
            } else {
                    openEquippedItem(selection-1, area);
                }
        }while(Main.chesting);
        clear();
        if(Main.chesting) {
            openInventory(area);
        }
    }

    private void printLoadout(){
        System.out.println("Loadout\n");
        for(int i = 0; i < 6; i++){
            if(loadout.get(i) != null) {
                System.out.println("\t[" + (i + 1) + "]:"  + header[i] + loadout.get(i).getName());
            } else {
                System.out.println("\t[" + (i+1) + "]:"  + header[i] + "Empty");
            }
        }
        System.out.print("\n\t[0]: Back to Inventory\n\n>");
    }

    private void openEquippedItem(int selection, Room area){
        if(loadout.get(selection) != null) {
            clear();
            Item item = loadout.get(selection);
            System.out.println(item.getName() );
            System.out.println(item.description);
            item.printStats();
            System.out.println("[1]: Unequip");
            System.out.println("[2]: Drop");
            System.out.print("[0]: Back\n\n>");
            int choice = Main.input.nextInt();
            if (choice == 1) {
                inventory.add(item);
                loadout.set(selection, null);
                updateStats();
            } else if(choice == 2){
                loadout.set(selection, null);
                updateStats();
            }
        }
        clear();
        printLoadout();
    }

    private void openSpelllist(Room area){
        clear();
        System.out.println("Spellbook\n\n");
        printSpellbook();
        System.out.print("\t[0]: Back to Inventory\n\n>");
        int selection;
        do{
            selection = Main.input.nextInt();
            if(selection == 0){
                break;
            } else {
                openSpell(selection, area);
            }
        }while(Main.chesting);
        clear();
        if(Main.chesting) {
            openInventory(area);
        }
    }

    private void openStats(Room area){
        clear();
        System.out.println("Current Stats: ");
        System.out.println("\n\tHealth: " + health + "/" + maxHealth +
                "\n\tMana: " + mana + "/" + maxMana +
                "\n\tAttack Damage: " + attack + " (" + strength + "str)" +
                "\n\tDefense: " + defense +
                "\n\tAgility: " + agility +
                "\n\n[Enter] to go back");
        Main.input.nextLine();
        String thing = new String();
        thing = Main.input.nextLine();
        openInventory(area);
    }

    public void printSpellbook(){
        for(int i = 0; i < spellbook.size(); i++){
            System.out.println("\t[" + (i+1) + "]: " + spellbook.get(i).getName());
        }
    }

    public void openSpell(int sel, Room area){
        clear();
        System.out.println(spellbook.get(sel-1).getName());
        System.out.println("\n\tMana cost: " + spellbook.get(sel-1).getManaCost());
        System.out.println("\tDamage: " + spellbook.get(sel-1).getDamage() + " "
                + spellbook.get(sel-1).getDamageType());
        System.out.println("\tBonus Damage: " + spellbook.get(sel-1).getBonusDamage()
                + " " + spellbook.get(sel-1).getBonusDamageType());
        System.out.println("\n[Enter] to go back");
        Main.input.nextLine();
        String thing = new String();
        thing = Main.input.nextLine();
        openSpelllist(area);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");
    }

}
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
//TODO check for merge after merging an item to see if you can merge two tier 1 and aboves

public class Chest {
    int x;
    int y;
    ArrayList<Item> contents = new ArrayList<Item>();
    ItemList itemslist = new ItemList();
    SpellList spelllist = new SpellList();

    public Chest(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
    }

    private void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }

    // Sets the contents of the chest
    public void setItems() {
        int items = ThreadLocalRandom.current().nextInt(1, 4);
        for (int i = 0; i < items; i++) {
            Item item = generateNewItem();
            if (item.isUnique()) {
                item.setObtainable(false);
            }
            contents.add(item);
        }
    }

    // Makes a random new item that is obtainable
    public Item generateNewItem() {
        Item item;
        int iteration = 0;
        do{
            iteration++;
            double rand = (double) (ThreadLocalRandom.current().nextInt(0, 101)) /100;
            if(itemslist.items.get(iteration).getWeight() >=rand){
                item = itemslist.items.get(iteration);
                item.randomize();
                return item;
            }
            if(iteration >= itemslist.items.size()){
                iteration = 0;
            }
        }while(true);
    }

    // Opens the chest, and gives the items to the player inventory
    public void open(Player p) {
        clear();
        Main.chesting = true;
        System.out.println("You Found: \n\n"); //Add check for merge here
        for (int i = 0; i < this.contents.size(); i++) {
            Item initItem = this.contents.get(i);
            Armor pos_0;
            Shield pos_1;
            Sword pos_2;
            Wand pos_3;
            if(initItem.typeConverter.equals("Armor")){
                pos_0 = (Armor)initItem;
                giveToPlayer(p, pos_0);
            } else if(initItem.typeConverter.equals("Shield")){
                pos_1 = (Shield)initItem;
                giveToPlayer(p, pos_1);
            } else if(initItem.typeConverter.equals("Sword")){
                pos_2 = (Sword)initItem;
                giveToPlayer(p, pos_2);
            } else if(initItem.typeConverter.equals("Wand")){
                pos_3 = (Wand)initItem;
                giveToPlayer(p, pos_3);
            } else {
                giveToPlayer(p, (Consumable)initItem);
            }
        }
        System.out.println("[Enter] to continue");
        String buffer = "";
        buffer = Main.input.nextLine();
        Main.chesting = false;
        clear();
    }

    public void giveToPlayer(Player p, Armor a){
        boolean merge = mergeCheck(p, a);
        if(!merge) {
            p.inventory.add(a);
            for (int x = 0; x < a.getName().length(); x++) {
                System.out.print(a.getName().charAt(x));
                wait(25);
            }
            System.out.println("\n");
            for (int j = 0; j < a.description.length(); j++) {
                System.out.print(a.description.charAt(j));
                wait(25);
            }
        } else {
            String printer = a.getName() + " Merged with a duplicate item, giving the existing copy a +5% stat increase!";
            for (int x = 0; x < printer.length(); x++) {
                System.out.print(printer.charAt(x));
                wait(25);
            }
        }
        System.out.print("\n----------------------------\n");
    }

    public void giveToPlayer(Player p, Consumable c){
            p.inventory.add(c);
            for (int x = 0; x < c.getName().length(); x++) {
                System.out.print(c.getName().charAt(x));
                wait(25);
            }
            System.out.println("\n");
            for (int j = 0; j < c.description.length(); j++) {
                System.out.print(c.description.charAt(j));
                wait(25);
            }
        System.out.print("\n----------------------------\n");
    }

    public void giveToPlayer(Player p, Shield s){
        boolean merge = mergeCheck(p, s);
        if(!merge) {
            p.inventory.add(s);
            for (int x = 0; x < s.getName().length(); x++) {
                System.out.print(s.getName().charAt(x));
                wait(25);
            }
            System.out.println("\n");
            for (int j = 0; j < s.description.length(); j++) {
                System.out.print(s.description.charAt(j));
                wait(25);
            }
        } else {
            String printer = s.getName() + " Merged with a duplicate item, giving the existing copy a +5% stat increase!";
            for (int x = 0; x < printer.length(); x++) {
                System.out.print(printer.charAt(x));
                wait(25);
            }
        }
        System.out.print("\n----------------------------\n");
    }

    public void giveToPlayer(Player p, Sword s){
        boolean merge = mergeCheck(p, s);
        if(!merge) {
            p.inventory.add(s);
            for (int x = 0; x < s.getName().length(); x++) {
                System.out.print(s.getName().charAt(x));
                wait(25);
            }
            System.out.println("\n");
            for (int j = 0; j < s.description.length(); j++) {
                System.out.print(s.description.charAt(j));
                wait(25);
            }
        } else {
            String printer = s.getName() + " Merged with a duplicate item, giving the existing copy a +5% stat increase!";
            for (int x = 0; x < printer.length(); x++) {
                System.out.print(printer.charAt(x));
                wait(25);
            }
        }
        System.out.print("\n----------------------------\n");
    }

    public void giveToPlayer(Player p, Wand w){
        boolean merge = mergeCheck(p, w);
        if(!merge) {
            p.inventory.add(w);
            for (int x = 0; x < w.getName().length(); x++) {
                System.out.print(w.getName().charAt(x));
                wait(25);
            }
            System.out.println("\n");
            for (int j = 0; j < w.description.length(); j++) {
                System.out.print(w.description.charAt(j));
                wait(25);
            }
        } else {
            String printer = w.getName() + " Merged with a duplicate item, giving the existing copy a +5% stat increase!";
            for (int x = 0; x < printer.length(); x++) {
                System.out.print(printer.charAt(x));
                wait(25);
            }
        }
        System.out.print("\n----------------------------\n");
    }

    public boolean mergeCheck(Player p, Armor a){
        boolean merge = false;
        for(int iter = 0; iter < p.inventory.size(); iter++){
            Item current = p.inventory.get(iter);
            Armor pos_0;
            if(current.typeConverter.equals("Armor")){
                pos_0 = (Armor)current;
               if(pos_0.name.equals(a.name)){
                    pos_0.merge(pos_0, a);
                    merge = true;
                    break;
                }
            }
        }
        return merge;
    }

    public boolean mergeCheck(Player p, Shield s){
        boolean merge = false;
        for(int iter = 0; iter < p.inventory.size(); iter++){
            Item current = p.inventory.get(iter);
            Shield pos_0;
            if(current.typeConverter.equals("Shield")){
                pos_0 = (Shield)current;
                if(pos_0.name.equals(s.name)){
                    pos_0.merge(pos_0, s);
                    merge = true;
                    break;
                }
            }
        }
        return merge;
    }

    public boolean mergeCheck(Player p, Sword s){
        boolean merge = false;
        for(int iter = 0; iter < p.inventory.size(); iter++){
            Item current = p.inventory.get(iter);
            Sword pos_0;
            if(current.typeConverter.equals("Sword")){
                pos_0 = (Sword)current;
                if(pos_0.name.equals(s.name)){
                    pos_0.merge(pos_0, s);
                    merge = true;
                    break;
                }
            }
        }
        return merge;
    }

    public boolean mergeCheck(Player p, Wand w){
        boolean merge = false;
        for(int iter = 0; iter < p.inventory.size(); iter++){
            Item current = p.inventory.get(iter);
            Wand pos_0;
            if(current.typeConverter.equals("Wand")){
                pos_0 = (Wand)current;
                if(pos_0.name.equals(w.name)){
                    pos_0.merge(pos_0, w);
                    merge = true;
                    break;
                }
            }
        }
        return merge;
    }
}
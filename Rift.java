import java.util.concurrent.ThreadLocalRandom;

class Rift{

    int xPos;
    int yPos;
    int roomIndex;
    boolean linked;
    int xLinked;
    int yLinked;
    int linkedRoomIndex;

    public Rift(int x, int y, boolean l){
        xPos = x;
        yPos = y;
        linked = l;
        roomIndex = Main.currentRoom;
    }

    //Links rifts to other rifts in other rooms
    public void link(int x, int y){
        linked = true;
        xLinked = x;
        yLinked = y;
       linkedRoomIndex = Main.currentRoom;
    }

    public void enter(){
        //Go thru linked rift TODO
        if(linked){
            //TODO something
        } else {
            roomIndex = Main.dungeon.size() + 1;
            //generate new room and add it to the arraylist
            int x = ThreadLocalRandom.current().nextInt(5, 16);
            int y = ThreadLocalRandom.current().nextInt(5, 16);
            boolean boss = false;
            if(Main.roomsUntilBoss == 0){
                boss = true;
            } else{
                Main.roomsUntilBoss --;
            }
            Room next = new Room(x, y, Main.currentFloor,  false, boss);
            Main.dungeon.add(next);
            next.setRoomIndex();
            Main.currentRoom = next.roomIndex-1;
            //TODO link();
        }
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

}
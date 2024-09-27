import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Main {

    public static boolean chesting = false;
    public static Scanner input = new Scanner(System.in);
   public   static int currentFloor = 1;
    public static int currentRoom = 0;
    public static ArrayList<Room> dungeon = new ArrayList<Room>();
    public static int roomsUntilBoss = ThreadLocalRandom.current().nextInt(4, 8); //TODO adjust values


    public static void clear(){
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args) {
        Room testRoom = new Room(10, 10, currentFloor, false, false);
        testRoom.setRoomIndex();;
        dungeon.add(testRoom);
        Player character = new Player(10, 5, 1);
        do{
            dungeon.get(currentRoom).printRoom();
            System.out.println("\n");
            System.out.print("Input: ");
            if(!chesting){
                char action = input.nextLine().charAt(0);
                if(action == 'q'){
                    break;
                }else {
                    character.move(action, dungeon.get(currentRoom));
                }
                clear();
            }
        }while(true);
        input.close();
    }
}
//TODO animation class
//TODO exists in: Boss, Chest(Do later), Enemy, Player (Do after implementing combat), Portal, Rift, and Room
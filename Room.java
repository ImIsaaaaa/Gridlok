import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
//TODO check if room population of rifts and enemies is functioning properly, sometimes you can't rotate your facing direction when rotating into a chest or monster
public class Room{

    int sizeX;
    int sizeY;
    int floor;
    String[][] roomArray;
    boolean bossRoom;
    boolean spawnFromDoor;
    int pX;
    int pY;
    Portal floorPortal;
    Boss floorBoss;
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    ArrayList<Rift> riftList = new ArrayList<Rift>();
    ArrayList<Chest> chestList = new ArrayList<Chest>();
    int roomIndex;
    //Tiles
    final static String emptyTile = "[ ]";
    final static String playerTileUp = "[↑]";
    final static String playerTileDown = "[↓]";
    final static String playerTileLeft = "[←]";
    final static String playerTileRight = "[→]";
    final static String enemyTileBasic = "[✝︎]"; //Combat (advantage to initiator)
    final static String enemyTileIntermediate = "[♰]"; //Combat (Advantage to Initiator)
    final static String enemyTileHard = "[✞]"; //Combat (Advantage to Initiator))
    final static String chestTile = "[⬭]"; //Manual Interaction -> Open
    final static String riftTile = "[⬦]"; //Manual Interaction -> Teleport
    final static String blockTile = "[■]";
    final static String bossTile = "[♱]"; //Manual/Automatic Interaction -> Boss
    final static String portalTile = "[⬯]"; //Automatic Interaction -> Portal

    public Room(int x, int y, int f, boolean d, boolean b){
        sizeX = x;
        sizeY = y;
        floor = f;
        roomArray = new String[sizeX][sizeY];
        spawnFromDoor = d;
        bossRoom = b;
        if(!bossRoom){
            populate();
        }else{
            generateBossRoom();
        }
    }

    public void setRoomIndex(){
        this.roomIndex = Main.dungeon.size();
    }

    //Generates the amount of enemies a room can spawn (TODO populate list)
    public int genNumEnemies(){ //PING ENEMIES
        int roomSize = sizeX * sizeY;
        if(roomSize < 49){
            return 1;
        } else if(roomSize < 100){
            return ThreadLocalRandom.current().nextInt(2, 5);
        } else if (roomSize < 225){
            return ThreadLocalRandom.current().nextInt(4, 6);
        } else {
            return ThreadLocalRandom.current().nextInt(5, 9);
        }
    }

    //Generates the amount of chests a room can have
    public int genNumChests(){
        int roomSize = sizeX * sizeY;
        if(roomSize < 49){
            return 0;
        } else if(roomSize < 100){
            return 1;
        } else if (roomSize < 225){
            return ThreadLocalRandom.current().nextInt(1, 4);
        } else {
            return ThreadLocalRandom.current().nextInt(2, 5);
        }
    }

    //Generates the number of rifts a room can have
    public int genNumRifts(){ //PING RIFTS
        int roomSize = sizeX * sizeY;
        int rifts = 0;
        if(!spawnFromDoor){
            rifts ++;
        }
        if(Main.roomsUntilBoss >= 0){
            rifts ++;
        }
        if(roomSize < 49){
            return rifts;
        } else if(roomSize < 100){
            return rifts + ThreadLocalRandom.current().nextInt(0, 2);
        } else if (roomSize < 225){
            return rifts + ThreadLocalRandom.current().nextInt(0, 3);
        } else {
            return rifts + ThreadLocalRandom.current().nextInt(0, 4);
        }
    }

    //returns the type of enemy that should spawn
    public String getScaling(){
        if(!spawnFromDoor || floor <= 1){
            return enemyTileBasic;
        } else if(floor < 3){
            return enemyTileIntermediate;
        } else{
            return enemyTileHard;
        }
    }

    //Fills the room with tiles (x and y are backwards lmao), need to adjust if spawned from a rift (TODO)
    public void populate(){
        int enemsToPlace = genNumEnemies();
        int chestsToPlace = genNumChests();
        int rifts = genNumRifts();
        String enemyType = getScaling();
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                boolean specialPlaced = false;
                if(x == sizeX/2 && y == sizeY/2 && !spawnFromDoor){
                    roomArray[x][y] = playerTileUp;
                    specialPlaced = true;
                    pX = x;
                    pY = y;
                } else if(enemsToPlace != 0 || chestsToPlace != 0 || rifts != 0){
                    int tilesToPlace = (sizeX * sizeY) - (x*sizeY + y);
                    if(ThreadLocalRandom.current().nextInt(0, tilesToPlace) == 0){
                        int rand = roomGenerate(enemsToPlace, chestsToPlace, rifts);
                        if(rand < 50){
                            roomArray[x][y] = enemyType;
                            enemsToPlace --;
                            enemyList.add(new Enemy(floor, spawnFromDoor, x, y));
                            specialPlaced = true;
                        } else if(rand < 100){
                            roomArray[x][y] = chestTile;
                            chestsToPlace --;
                            chestList.add(new Chest(x, y));
                            chestList.get(chestList.size()-1).setItems();
                            specialPlaced = true;
                        } else if (rand < 150){
                            roomArray[x][y] = riftTile;
                            rifts --;
                            riftList.add(new Rift(x, y, false));
                            specialPlaced = true;
                        }
                    }
                }
                if(!specialPlaced){
                    if(ThreadLocalRandom.current().nextInt(1, 5) == 1){
                        roomArray[x][y] = blockTile;
                    } else{
                        roomArray[x][y] = emptyTile;
                    }
                }
            }
        }
    }

    public int roomGenerate(int e, int c, int r){
        if(c == e && e == r && r == 0){
            return 255;
        }
        while(true){
            int rand = ThreadLocalRandom.current().nextInt(0, 150);
            if(rand < 50 && e > 0){
                return rand;
            } else if(rand < 100 && c > 0){
                return rand;
            } else if(r > 0){
                return rand;
            }
        }
    }

    //Generates the room if it's a boss room
    public void generateBossRoom(){
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                if(x == sizeX / 3 && y == sizeY / 2){
                    roomArray[x][y] = bossTile;
                    floorPortal = new Portal(x, y);
                    floorBoss = new Boss(floor, x, y);
                } else if(x == sizeX / 3 * 2 && y == sizeY / 2){
                    roomArray[x][y] = playerTileUp;
                    pX = x;
                    pY = y;
                } else if (x == pX && y == pY+1){
                    roomArray[x][y] = riftTile; //TODO link this tile
                }else {
                    roomArray[x][y] = emptyTile;
                }
            }
        }
    }

    //Updates the room after each turn
    public void update(char dir){
        //resolve move
        System.out.println("dir: " + dir);
        if(dir == 'n' && checkBounds(pY, pX, dir)){
            if(roomArray[pX-1][pY].equals(emptyTile)){
                pX--;
                roomArray[pX][pY] = playerTileUp;
                roomArray[pX+1][pY] = emptyTile;
            } else {
                movementBlocked(roomArray[pX-1][pY], dir, pX, pY);
            }
        } else if(dir == 's' && checkBounds(pY, pX, dir)){
            if(roomArray[pX+1][pY].equals(emptyTile)){
                pX++;
                roomArray[pX][pY] = playerTileDown;
                roomArray[pX-1][pY] = emptyTile;
            }else{
                movementBlocked(roomArray[pX+1][pX], dir, pX, pY);
            }
        } else if(dir == 'e' && checkBounds(pY, pX, dir)){
            if(roomArray[pX][pY+1].equals(emptyTile)){
                pY++;
                roomArray[pX][pY] = playerTileRight;
                roomArray[pX][pY-1] = emptyTile;
            } else{
                movementBlocked(roomArray[pX][pY+1], dir, pX, pY);
            }
        } else if(dir == 'w'  && checkBounds(pY, pX, dir)){
            if(roomArray[pX][pY-1].equals(emptyTile)){
                pY--;
                roomArray[pX][pY] = playerTileLeft;
                roomArray[pX][pY+1] = emptyTile;
            } else{
                movementBlocked(roomArray[pX][pY-1], dir, pX, pY);
            }
        }
        for(int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).move(); //TODO Function
        }
    }

    //Checks if the tile the player wants to move to/interact with exists
    public boolean checkBounds(int x, int y, char dir){
        if(dir == 'n'){
            return y > 0;
        } else if (dir == 's'){
            return y <= sizeX - 2;
        } else if (dir == 'e'){
            return x <= sizeY - 2;
        } else if(dir == 'w'){
            return x > 0;
        }
        return false;
    }

    //Updates a specific tile (if an enemy gets defeated, etc)
    public void updateTile(int x, int y){
        roomArray[x][y] = emptyTile;
    }

    public void movementBlocked(String tile, char direction, int pX, int pY){
        if(tile.equals(chestTile) || tile.equals(portalTile) || tile.equals(riftTile) || tile.equals(blockTile)){
            if(direction == 'n'){
                roomArray[pX][pY] = playerTileUp;
            } else if(direction == 's'){
                roomArray[pX][pY] = playerTileDown;
            } else if(direction == 'e'){
                roomArray[pX][pY] = playerTileRight;
            } else if(direction == 'w'){
                roomArray[pX][pY] = playerTileLeft;
            }
        }
    }

    //Resolves player interaction, if player does that instead of move
    public void interact(char action, Player character){
        char facing = getPlayerDirection();
        String tileToInteract = blockTile;
        int xI = -1;
        int yI = -1;
        if(facing == 'n' && checkBounds(pY, pX, facing)){
            tileToInteract = roomArray[pX-1][pY];
            xI = pX-1;
            yI = pY;
        } else if (facing == 's' && checkBounds(pY, pX, facing)){
            tileToInteract = roomArray[pX+1][pY];
            xI = pX+1;
            yI = pY;
        } else if(facing == 'w' && checkBounds(pY, pX, facing)){
            tileToInteract = roomArray[pX][pY-1];
            xI = pX;
            yI = pY-1;
        } else if(facing == 'e' && checkBounds(pY, pX, facing)) {
            tileToInteract = roomArray[pX][pY+1];
            xI = pX;
            yI = pY+1;
        }
        if(action == 'a'){
            if(tileToInteract.equals(enemyTileHard)){
                for(int i = 0; i < enemyList.size(); i++){
                    if(enemyList.get(i).getX() == xI && enemyList.get(i).getY() == yI){
                        enemyList.get(i).enterCombat(character, 'p');
                        enemyList.remove(i);
                        break;
                    }
                }
                updateTile(xI, yI);
                printRoom();//TODO remember to use System.exit in combat loop if player dies
            } else if(tileToInteract.equals(enemyTileIntermediate)){
                for(int i = 0; i < enemyList.size(); i++){
                    if(enemyList.get(i).getX() == xI && enemyList.get(i).getY() == yI){
                        enemyList.get(i).enterCombat(character, 'p');
                        enemyList.remove(i);
                        break;
                    }
                }
                updateTile(xI, yI);
                printRoom();//TODO remember to use System.exit in combat loop if player dies
            } else if(tileToInteract.equals(enemyTileBasic)){
                for(int i = 0; i < enemyList.size(); i++){
                    if(enemyList.get(i).getX() == xI && enemyList.get(i).getY() == yI){
                        enemyList.get(i).enterCombat(character, 'p');
                        enemyList.remove(i);
                        break;
                    }
                }
                updateTile(xI, yI);
                printRoom();//TODO remember to use System.exit in combat loop if player dies
            } else if(tileToInteract.equals(bossTile)){
                floorBoss.enterCombat(character, 'p');
                updateTile(xI, yI); //Update to portal tile TODO
                printRoom();
            } else if(tileToInteract.equals(blockTile)){
                updateTile(xI, yI);
                printRoom();
            }
        } else if(action == 'e'){
            if(tileToInteract.equals(chestTile)){
                for(int i = 0; i < chestList.size(); i++){
                    if(chestList.get(i).getX() == xI && chestList.get(i).getY() == yI){
                        chestList.get(i).open(character);
                        chestList.remove(i);
                        break;
                    }
                }
                updateTile(xI, yI);
                printRoom();
            } else if(tileToInteract.equals(riftTile)){
                for(int i = 0; i < riftList.size(); i++){
                    if(riftList.get(i).getX() == xI && riftList.get(i).getY() == yI){
                        riftList.get(i).enter();
                        break;
                    }
                }
            } else if(tileToInteract.equals(portalTile)){
                floorPortal.enter();
            } else if(!tileToInteract.equals(blockTile)){
                for(int i = 0; i < enemyList.size(); i++){
                    if(enemyList.get(i).getX() == xI && enemyList.get(i).getY() == yI){
                        enemyList.get(i).enterCombat(character, 'n'); //METHOD TODO
                        break;
                    }
                }
                updateTile(xI, yI);
                printRoom();
            }
        }
    }

    //Returns the compass direction the player is facing
    public char getPlayerDirection(){
        char dir = 'n';
        String player = roomArray[pX][pY];
        if(player.equals(playerTileUp)){
            dir = 'n';
        } else if(player.equals(playerTileDown)){
            dir = 's';
        } else if(player.equals(playerTileLeft)){
            dir = 'w';
        } else if(player.equals(playerTileRight)){
            dir = 'e';
        }
        return dir;
    }

    //Prints the room
    public void printRoom(){
        for(int x = 0; x < sizeX; x++){
            for(int y = 0; y < sizeY; y++){
                System.out.print(roomArray[x][y]);
            }
            System.out.println();
        }
    }
}
public class GameManager {
    private String playerName;
    private int credits;

    private Ship[] ships = new Ship[0];
    private Person[] crew = new Person[0];

    GameManager() {
//        addShip
    }

    //Game Loop

    void Update() {

    }

    //Print Output

    public void printFleet() {
        for(Ship s : ships) {
            System.out.print("Ship #" + s.getNumber() + ": " + s.getName());
            if(ships.length > 1) {
                for(int i = s.getName().length(); i < getLongestShipName(); i++) {
                    System.out.print(" ");
                }
            }
            for(int i = 0; i < s.getStats().length; i++) {
                System.out.print(" | " + s.getStat(i) + " " + s.getStatName(i));
            }
            System.out.println();
        }
    }
    public void printCrew() {

    }

    //Ship-Related

    public void addShip(Ship s) {
        Ship[] t = new Ship[ships.length + 1];
        for(int i = 0; i < ships.length; i++) {
            t[i] = ships[i];
        }
        t[ships.length] = s;
        ships = t;
    }
    public void removeShip(int shipNumber) {
        int removeIndex = -1;

        for(int i = 0; i < ships.length; i++) {
            if (ships[i].getNumber() == shipNumber) {
                removeIndex = i;
            }
        }

        if(removeIndex != -1) {
            Ship t[] = new Ship[ships.length - 1];

            for(int i = 0; i < removeIndex; i++) {
                t[i] = ships[i];
            }

            for(int i = removeIndex; i < t.length; i++) {
                t[i] = ships[i+1];
            }
            ships = t;
        } else {
            System.out.println("HEY YOU FAILED TO REMOVE THE RIGHT SHIP, " + shipNumber + " IS NOT A VALID NUMBER!!!");
        }
    }

    public Ship[] getShips() {
        return ships;
    }
    public Ship getShip(int index) {
        return ships[index];
    }

    public int getLongestShipName() {
        int x = 0;
        if(ships.length > 1) {
            for(Ship s : ships) {
                if(s.getName().length() > 0) {
                    x = s.getName().length();
                }
            }
            return x;
        } else return ships[0].getName().length();

    }


    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String _playerName) {
        playerName = _playerName;
    }
}

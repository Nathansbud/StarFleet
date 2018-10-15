public class GameManager {
    private String playerName;
    private int credits;

    private Ship[] ships = new Ship[1];
    private Person[] crew;

    GameManager() {
        ships[0] = new Ship("SS Dad Bod", new int[]{100, 10, 10, 10});

    }

    void Update() {

    }


    public void AddShip(Ship s) {
        Ship[] t = new Ship[ships.length + 1];
        for(int i = 0; i < ships.length; i++) {
            t[i] = ships[i];
        }
        t[t.length - 1] = s;
        ships = t;
    }


    public Ship[] getShips() {
        return ships;
    }
    public Ship getShip(int index) {
        return ships[index];
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String _playerName) {
        playerName = _playerName;
    }



}

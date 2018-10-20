public class Ship {
    /*
        Stats:
            - Health
            - Armor
            - Damage
            - Speed
     */

    private static int SHIP_NUMBER = 1;

    private String name;

    private int stats[];
    private String statNames[] = {
            "HP", "DEF", "ATT", "SPD"
    };

    private int number = 0;
    private int reward = 0;

    Ship(String _name, int[] _stats) {
        name = _name;
        stats = _stats;
        number = SHIP_NUMBER++;
    }

    Ship(String _name, int[] _stats, int _reward) {
        name = _name;
        stats = _stats;
        reward = _reward;
    }


    //Print Functions
    public void print() {
        System.out.print("Ship #" + number + ": " + name);
        for(int i = 0; i < stats.length; i++) {
            System.out.print(" | " + stats[i] + " " + statNames[i]);
        }
        System.out.println();
    }

    public void battlePrint() {
        if(number > 0) {
            System.out.print("Player: " + name);
        } else {
            System.out.print("Enemy: " + name);
        }
        for(int i = 0; i < stats.length; i++) {
            System.out.print(" | " + stats[i] + " " + statNames[i]);
        }
        System.out.println();
    }

    //Get/Set Functions

    public String getName() {
        return name;
    }
    public void setName(String _name) {
        name = _name;
    }

    public int getNumber() {
        return number;
    }
    public static int getShipNumber() {
        return SHIP_NUMBER;
    }

    public int[] getStats() {
        return stats;
    }
    public void setStats(int[] _stats) {
        stats = _stats;
    }

    public int getStat(int index) {
        return stats[index];
    }
    public void setStat(int index, int _stat) {
        stats[index] = _stat;
    }

    public String getStatName(int index) {
        return statNames[index];
    }
    public int getHealth() {
        return stats[0];
    }
    public void setHealth(int _health) {
        stats[0] = _health;
    }

    public int getArmor() {
        return stats[1];
    }
    public void setArmor(int _armor) {
        stats[1] = _armor;
    }

    public int getDamage() {return stats[2];}
    public void setDamage(int _damage) {
        stats[2] = _damage;
    }

    public int getSpeed() {return stats[3];}
    public void setSpeed(int _speed) {
        stats[3] = _speed;
    }

    public int getReward() {
        return reward;
    }
}

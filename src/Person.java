public class Person {
    /*
        Stats:
            - Health
            - Damage
            - Int
            - Dex
     */

    private enum Role {
        PILOT,
        MECHANIC
    }


    private String name;

    private int stats[] = new int[4];
    private String statNames[] = {"HP", "DMG", "INT", "DEX"};

    private int level;

    Person() {

    }


    public int[] getStats() {
        return stats;
    }
    public void setStats(int[] _stats) {
        stats = _stats;
    }

    public String[] getStatNames() {
        return statNames;
    }
    public String getStatName(int index) {return statNames[index];}

    public int getHealth() {return stats[0];}
    public int getDamage() {return stats[1];}
    public int getInt() {return stats[2];}
    public int getDex() {return stats[3];}

    public String getName() {
        return name;
    }
    public void setName(String _name) {
        name = _name;
    }
}

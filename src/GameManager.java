public class GameManager {
    private String playerName;
    private int credits = 100;

    private Ship[] ships = new Ship[0];

    private String[] missions[] = {
            {"Uranium Mining Expedition", "Short 2", "Short 3"}, //Short
            {"Sargaid Retrieval", "Med 2", "Med 3"}, //Medium
            {"X'argon Raid", "Long 2", "Long 3"} //Long
    };

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
    public void printUpdate() {
        System.out.println("Credits: " + credits);
        System.out.println();
        printFleet();
        System.out.println();
        System.out.println("Which ship would you like to choose?");
    }
    public void printCredits() {
        System.out.println("Credits: " + credits);
    }
    public void printShip(int index) {
        ships[index].print();
    }

    public void printMission(int difficulty, int mission, int ship) {
        switch(difficulty) {
            case 1:
                switch(mission) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch(mission) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch(mission) {
                    case 0:
                        Ship enemyShip = new Ship("X'argon Warship", new int[]{100, 15, 5, 5}, 100);

                        dialogue("Boarding " + ships[ship].getName() + ", you set off towards the X'argon Home World.", 3.5f, true);
                        dialogue("The X'argon, a notoriously tough race of telepathic bipeds, have something you want — treasure!", 3.5f, false);
                        dialogue("The opulence of the X'argon Court is a poorly-guarded secret, but the kingdom's guard is...well, anything but poor.", 3.5f, true);
                        dialogue("Unfortunately, your cryo-sleep is cut short by the blaring of the auto-pilot system:", 3, true);
                        for(int i = 0; i < 4; i++) {
                            dialogue("HOSTILE SHIP DETECTED. TRANSMISSION INBOUND.", 1, false);
                        }
                        System.out.println();

                        dialogue("Looks like the X'argon don't take too kindly to invaders...you're not sure if the X'argon's telepathic abilities " +
                                "will affect you if you accept the transmission, but you're not willing to take that risk!", 5, true);
                        dialogue(ships[ship].getName() + ": ATTACKS ENGAGED", 1, false);
                        dialogue(ships[ship].getName() + ": DEFENSES ENGAGED", 2, true);
                        dialogue("PREPARING FOR BATTLE!", 1.5f, true);

                        ships[ship].battlePrint();
                        enemyShip.battlePrint();

                        System.out.println();

                        if(!battle(ships[ship], enemyShip)) {
                            removeShip(ships[ship].getNumber());
                            break;
                        }
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
                break;
            default:
                System.out.println("this shouldn't happen");
                break;
        }
    }

    //Battle-Related

    public boolean battle(Ship a, Ship b) {
        int turn = 1;
        float dialogueWaitTime = 3;

        boolean success = false;
        boolean isBattle = true;

        Ship[] s = {a, b};
        int[] dmg = {0, 0};
        int[] dodge = {0, 0};
        int[] hp = {0, 0};
        int[] armor = {0, 0};

        while(isBattle) {
            for(int i = 0; i < s.length; i++) {
                dmg[i] = s[i].getDamage() + (int)(Math.random()*s[i].getDamage()+1);
                dodge[i] = (int)(Math.random()*100+1);
                armor[i] = s[i].getArmor();
                hp[i] = s[i].getHealth();
            }

            dialogue("[Turn " + turn + "]", dialogueWaitTime, true);

            for(int i = 0, j = 1; i < s.length; i++, j--) {
                dialogue(s[i].getName() + " attempts to attack " + s[j].getName() + " for " + dmg[i] + " damage!", dialogueWaitTime, false);
                if(dodge[j] < s[j].getSpeed()) {
                    dialogue(s[j].getName() + " dodged the attack!", dialogueWaitTime, true);
                } else {
                    if(s[j].getArmor() > 0) {
                        s[j].setArmor(s[j].getArmor() - dmg[i]);
                        if(s[j].getArmor() < 0) {
                            s[j].setHealth(s[j].getHealth() - Math.abs(s[j].getArmor()));
                            s[j].setArmor(0);
                        }
                    } else {
                        s[j].setHealth(s[j].getHealth() - dmg[i]);
                    }

                    if(armor[j] > 0 && s[j].getArmor() > 0) {
                        dialogue(s[j].getName() + " loses " + (armor[j] - s[j].getArmor()) + " points of armor!", dialogueWaitTime, true);
                    } else if(armor[j] > 0 && s[j].getHealth() == hp[j]) {
                        dialogue(s[j].getName() + " loses " + (armor[j]) + " points of armor, breaking its armor!", dialogueWaitTime, true);
                    } else if(armor[j] > 0 && (hp[j] - s[j].getHealth()) > 0) {
                        dialogue(s[j].getName() + " loses " + (armor[j]) + " points of armor, breaking its armor, and " + (hp[j] - s[j].getHealth()) + " health!", dialogueWaitTime, true);
                    } else {
                        dialogue(s[j].getName() + " loses " + (hp[j] - s[j].getHealth()) + " health!", dialogueWaitTime, true);
                    }

                    if(s[j].getHealth() < 0) {
                        if (s[i].getNumber() > 0) {
                            s[i].battlePrint();
                            dialogue("Received " + s[j].getReward() + " credits!", dialogueWaitTime, false);
                            credits += s[j].getReward();
                            success = true;
                        } else {
                            dialogue("Despite your best efforts, " + s[j].getName() + " was destroyed. Luckily, you managed to strap into a cryo-escape pod, directed back towards your home base. Next time you might not be so lucky...", 5, true);
                            success = false;
                        }
                        isBattle = false;
                        break;
                    }
                }
                System.out.println();
            }
            if(isBattle) {
                s[0].battlePrint();
                s[1].battlePrint();
                dialogue("", dialogueWaitTime, false);
            }
            turn++;
        }
        return success;
    }

    //Text-Related

    public void dialogue(String text, float waitTime, boolean lineSpace) {
        System.out.println(text);
        consolePause(waitTime);
        if(lineSpace) {
            System.out.println();
        }
    }
    public void consolePause(float seconds) {
        try {
            Thread.sleep((long)(seconds*1000));
        } catch(InterruptedException e) {

        }
    }

    //Mission-Related

    public String[][] getAllMissions() {
        return missions;
    }
    public String[] getMissions(int difficulty) {
        return missions[difficulty - 1];
    }
    public String getMission(int difficulty, int index) {
        return missions[difficulty - 1][index];
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
    public int getShipCount() {return ships.length;}

    public int getCredits() {
        return credits;
    }
    public void setCredits(int _credits) {
        credits = _credits;
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

    //Player Related

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String _playerName) {
        playerName = _playerName;
    }
}

import java.util.Scanner;
import java.util.Stack;


public class StarFleet {

    private static Scanner sc = new Scanner(System.in);
    private static GameManager g;

    private static Stack<Integer> menuHistory = new Stack<Integer>();
    private static int menuState = 2;
    private static boolean isInt = false;

    private static boolean isOnMission = false;

    private static int shipChoice = 0;

    private static int missionDifficulty = 3;
    private static int missionChoice = 0;



    private static String menu[][] = {
            {"Error Zone"}, //0, Error Handle Zone!!!
            {"Quit?", "Yes", "No"}, //1, Quit Zone
            {"Hub", "Hangar", "Barracks", "Blacksmith"}, //2, Hub Area
            {"Hangar", "Upgrade", "Embark"}, //3, Hull Options
            {"Upgrade"}, //4, Upgrade Menu
            {"Upgrade Ship"}, //5, Upgrade Ship
            {"Mission Embark", "Short", "Medium", "Long"}, //6, Mission Embark
            {"Mission Embark (Ship Choice)"}, //Mission Embark (Ship Choice) {title will be overwritten}
            {"Mission Confirmation", "Yes", "No"},
            {"Mission"},
            {"Mission Results"}
    };

    private static String dict[] = {
            "/help",
            "/quit",
            "x",
            "/showfleet",
            "/back",
            "/dev_rs", //<shipNum: int>
            "/dev_as", //<shipName: string> <health: int> <def: int> <att: int> <spd: int>
            "/dev_pickmeup"
    };

    private static String dictExplanation[] = {
            "Displays a list of all available commands available, along with their arguments.",
            "Quits the game.",
            "",
            "",
            ""
    };

    public static void intro() {
        System.out.println("You're woken up by the smell of smoke, and the sound of static in your ear. To your left, a ship, wing bent in an awkward direction.");
        System.out.println("Something...must've gone wrong here. You're so muddled, you can't even remember you own name...");
        String s;
        boolean p;
        do {
            System.out.println("Is it...");
            s = sc.nextLine();
            System.out.println("Ah, yes, \"" + s + "\" is your name...right?");
            System.out.println();
            System.out.println("1: Yes, 100%!");
            System.out.println("2: No, that's not quite it...");
            System.out.println();
            if(getBoundedIntChoice(1, 2) == 1) {
                p = true;
                sc.nextLine();
            } else {
                p = false;
                sc.nextLine();
            }
        } while(!p);

        g.setPlayerName(s);
        System.out.println("Yes, it's all coming back to you now...the mission, the crash, the...other...crew members?");
        do {
            System.out.println("The ship...it was named...");
            s = sc.nextLine();
            System.out.println("Ah, yes, \"" + s + "\"...right?");
            System.out.println();
            System.out.println("1: Yes, 100%!");
            System.out.println("2: No, that's not quite it...");
            System.out.println();
            if(getBoundedIntChoice(1, 2) == 1) {
                p = true;
                sc.nextLine();
            } else {
                p = false;
                sc.nextLine();
            }
        } while(!p);

        g.addShip(new Ship(s,  new int[]{100, 50, 10, 10}));
    }


    public static void main(String[] args) {
        g = new GameManager();
//        intro();
        g.addShip(new Ship("SS Dad Bod", new int[]{100, 50, 10, 10}));
        g.addShip(new Ship("SS Didgeridad", new int[]{100, 50, 10, 10}));
        g.addShip(new Ship("SS Dongerbois", new int[]{100, 50, 10, 10}));
        g.setPlayerName("Zack");

        menu[2][0] = (g.getPlayerName().charAt(g.getPlayerName().length() - 1) == ('s')) ?
                (g.getPlayerName() + "' Starfleet") :
                (g.getPlayerName() + "'s Starfleet");

        menuHistory.push(1);

        menuState = 8;

        boolean game = true;

        while(game) {
            System.out.println("["+menu[menuState][0]+"]");
            System.out.println("Menu State: " + menuState);
            System.out.println();
            switch(menuState) {
                case 3:
                    g.printFleet();
                    System.out.println();
                    break;
                case 4:
                    g.printUpdate();
                    System.out.println();
                    break;
                case 5:
                    g.printCredits();
                    g.getShip(shipChoice).print();
                    System.out.println();
                    break;
                case 6:
                    g.printFleet();
                    System.out.println();
                    break;
                case 7:
                    g.printFleet();
                    System.out.println();
                    System.out.println("Which ship would you like to choose?");
                    System.out.println();
                    break;
                case 8:
                    System.out.println("Mission Length: " + menu[6][missionDifficulty]);
                    g.getShip(shipChoice).print();
                    System.out.println();
                    break;
                case 9:
                    g.printMission(missionDifficulty, missionChoice, shipChoice);
                    break;
                case 10:
                    break;
                default:
                    break;
            }

            generateMenu(menuState);
            String c;

            switch(menuState) {
                case 0:
                case 9:
                case 10:
                    c = "0";
                    isInt = true;
                    break;
                default:
                    c = dataInput(1, menu[menuState].length - 1);
                    break;
            }

            int choice;

            if(isInt) {
                choice = Integer.parseInt(c);

                switch (menuState) {
                    case 1: //Exit Menu
                        switch (choice) {
                            case 2:
                                menuHistory.push(menuState);
                                menuState = 2;
                                break;
                            default:
                                game = false;
                                break;
                        }
                        break;
                    case 2: //Main Menu
                        switch (choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            case 1:
                                menuHistory.push(menuState);
                                menuState = 3;
                                break;
                            case 2:
                                menuHistory.push(menuState);
                                menuState = 4;
                                break;
                            case 3:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                            default:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                        }
                        break;

                    case 3: //Hanger
                        switch (choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            case 1:
                                menuHistory.push(menuState);
                                menuState = 4;
                                break;
                            case 2:
                                menuHistory.push(menuState);
                                menuState = 6;
                                break;
                            default:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                        }
                        break;
                    case 4: //Upgrade
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            default:
                                if(choice > 0) {
                                    if(choice <= g.getShipCount()) {
                                        shipChoice = choice - 1;
                                        menuHistory.push(menuState);
                                        menuState = 5;
                                    }
                                } else {
                                    System.out.println("Ship choice must be a valid option!");
                                }
                                break;
                        }
                        break;
                    case 5: //Upgrade Ship
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            default:
                                g.getShip(shipChoice);
                                break;
                        }
                        break;
                    case 6:
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            default:
                                menuHistory.push(menuState);
                                menuState = 7;
                                menu[menuState][0] = menu[6][choice] + " Mission";
                                missionDifficulty = choice;
                                missionChoice = ((int)(Math.random()*(g.getMissions(missionDifficulty).length)));
                                break;
                        }
                        break;
                    case 7:
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            default:
                                if (choice > 0 && choice <= g.getShipCount()) {
                                    shipChoice = choice - 1;
                                    menuHistory.push(menuState);
                                    menuState = 8;
                                } else {
                                    System.out.println("Ship choice must be a valid option!");
                                }
                                break;
                        }
                        break;
                    case 8:
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            case 1:
                                menuHistory.push(menuState);
                                menuState = 9;
                                menu[9][0] = g.getMission(missionDifficulty, missionChoice);
                                break;
                            case 2:
                                menuHistory.pop();
                                menuState = menuHistory.pop();
                                break;
                        }
                        break;
                    case 9:
                        menuState = 10;
                        break;
                    case 10:
                        menuState = 2;
                        break;
                    default: //Error Zone
                        System.out.println("Unfinished screen! Redirecting to previous screen...");
                        menuState = menuHistory.pop();
                        break;
                }
            } else {
                String[] command = c.split("\\s");
                switch(command[0]) {
                    case "/dev_sc":
                        if(command.length == 1) {
                            try {
                                g.setCredits(Integer.parseInt(command[1]));
                            } catch(NumberFormatException e) {
                                System.out.println("/dev_sc must take an a credit amount as input!");
                            }
                        }
                        break;
                    case "/dev_pickmeup":
                        System.out.println("Code not working? That sucks, man. Feel free to take a break if you need it!");
                        break;
                    case "/dev_rs":
                        if (command.length > 1) {
                            try {
                                g.removeShip(Integer.parseInt(command[1]));
                            } catch (NumberFormatException e) {
                                System.out.println("/dev_rs must take an index as input!");
                            }
                        } else {
                            System.out.println("/dev_rs must take an index as input!");
                        }
                        break;
                    case "/dev_as":
                        if(command.length == 6) {
                            try {
                                g.addShip(new Ship(command[1], new int[]{Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]), Integer.parseInt(command[5])}));
                            } catch(NumberFormatException e) {
                                System.out.println("/dev_as must take a string followed by 4 ints as inputs!");
                            }
                        } else {
                            System.out.println("/dev_as must take a string followed by 4 ints as inputs!");
                        }
                        break;
                    case "/help":
                        if(command.length > 1) {
                            System.out.println("This command takes no arguments!");
                        } else {
                            System.out.println("{Help}");
                            for (int i = 0; i < dict.length; i++) {
                                System.out.println("[" + dict[i] + "]: "); //+ dictExplanation[i]);
                            }
                        }
                        break;
                    case "/back":
                        menuState = menuHistory.pop();
                        break;
                    case "/quit":
                    case "x":
                        game = false;
                        break;
                    case "/showfleet":
                        g.printFleet();
                        break;
                    default:
                        System.out.println("Inputs must be a valid choice, or /help");
                        break;
                }
            }
        }
    }

    public static void generateMenu(int choice) {
        if(choice < menu.length) {
            String[] list = menu[choice];

            for (int i = 1; i < list.length; i++) {
                System.out.println((i) + ". " + list[i]);
            }
            System.out.println();
        }
    }
    private static String dataInput(int min, int max) {
        System.out.print("Choose: ");
        String s;
        isInt = false;

        s = sc.nextLine();
        try {
            int x = Integer.parseInt(s);
            if(max > min) {
                if ((!(x < min) && !(x > max)) || x == -1) {
                    isInt = true;
                }
            } else {
                isInt = true;
            }
        } catch(NumberFormatException e) {
            for(String d : dict) {
                s = s.toLowerCase();
                String a[] = s.split("\\s");
                if(a[0].equals(d)) {
                    isInt = false;
                }
            }
        }

        return s;
    }
    public static int getBoundedIntChoice(int min, int max) {
        System.out.print("Choose: ");
        int r;

        do {
            while(!sc.hasNextInt()) {
                System.out.print("Your input must be between " + min + " and " + max + ": ");
                sc.nextLine();
            }

            r = sc.nextInt();

            if(r > max || r < min) {
                System.out.println("Your input must be between " + min + " and " + max + ": ");
            }
        } while(r > max || r < min);

        return r;
    }
}

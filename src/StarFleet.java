import java.util.Scanner;
import java.util.Stack;


public class StarFleet {

    private static Scanner sc = new Scanner(System.in);
    private static GameManager g;

    private static Stack<Integer> menuHistory = new Stack<Integer>();
    private static int menuState = 2;
    private static boolean isInt = false;

    private static String menu[][] = {
            {"Error Zone"}, //0, Error Handle Zone!!!
            {"Quit?", "Yes", "No"}, //1, Quit Zone
            {"Hub", "Hangar", "Barracks", "Blacksmith"}, //2, Hub Area
            {"Hangar", "Upgrade", "Embark"}, //3, Hull Options
            {"Barracks", "Testing", "123"}  //4, Barracks
    };

    private static String dict[] = {
            "/help",
            "/quit",
            "x",
            "/showfleet",
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
            System.out.println("Ah, yes, \"" + s + "\" your my name...right?");
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

        g.addShip(new Ship("SS Dad Bod",  new int[]{100, 50, 10, 10}));
    }


    public static void main(String[] args) {
        g = new GameManager();
        g.addShip(new Ship("SS Dad Bod", new int[]{100, 50, 10, 10}));
        menuHistory.push(1);

        while(true) {
            System.out.println("["+menu[menuState][0]+"]");
            switch(menuState) {
                case 3:
                    g.printFleet();
                    break;
                default:
                    break;
            }

            generateMenu(menuState);
            String c;
            if(menuState > 0) {
                c = dataInput(1, menu[menuState].length - 1);
            } else {
                c = "0";
            }
            int choice;

            if(isInt) {
                choice = Integer.parseInt(c);

                switch (menuState) {
                    case 1:
                        switch (choice) {
                            case 2:
                                menuHistory.push(menuState);
                                menuState = 2;
                                break;
                            default:
                                System.exit(0);
                                break;
                        }
                        break;
                    case 2:
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

                    case 3:
                        switch (choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            case 1:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                            case 2:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                            default:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                        }
                        break;
                    case 4:
                        switch(choice) {
                            case -1:
                                menuState = menuHistory.pop();
                                break;
                            default:
                                menuHistory.push(menuState);
                                menuState = 0;
                                break;
                        }
                        break;
                    default:
                        System.out.println("YOU HAVE ENTERED THE SUPER SECRET ZONE!!!!");
                        System.out.println("pranked i just have programmed this screen yet/you typed the wrong thing!!");
                        System.out.println("Redirecting you to previous screen...");
                        menuState = menuHistory.pop();
                        break;
                }
            } else {
                String[] a = c.split("\\s");

                switch(a[0]) {
                    case "/dev_pickmeup":
                        System.out.println("Code not working? That sucks, man. Feel free to take a break if you need it!");
                        break;
                    case "/dev_rs":
                        if (a.length > 1) {
                            try {
                                g.removeShip(Integer.parseInt(a[1]));
                            } catch (NumberFormatException e) {
                                System.out.println("/dev_rs must take an index as input!");
                            }
                        } else {
                            System.out.println("/dev_rs must take an index as input!");
                        }
                        break;
                    case "/dev_as":
                        if(a.length == 6) {
                            try {
                                g.addShip(new Ship(a[1], new int[]{Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5])}));
                            } catch(NumberFormatException e) {
                                System.out.println("/dev_as must take a string followed by 4 ints as inputs!");
                            }
                        } else {
                            System.out.println("/dev_as must take a string followed by 4 ints as inputs!");
                        }
                        break;
                    case "/help":
                        if(a.length > 1) {
                            System.out.println("This command takes no arguments!");
                        } else {
                            System.out.println("{Help}");
                            for (int i = 0; i < dict.length; i++) {
                                System.out.println("[" + dict[i] + "]: "); //+ dictExplanation[i]);
                            }
                        }
                        break;
                    case "/quit":
                    case "x":
                        System.exit(0);
                        break;
                    case "/showfleet":
                        g.printFleet();
                        break;
                    default:
                        System.out.println("UNIMPLEMENTED");
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
        boolean passed = false;

        do {
            s = sc.nextLine();
            try {
                int x = Integer.parseInt(s);
                if((!(x < min) && !(x > max)) || x == -1) {
                    isInt = true;
                    passed = true;
                }
            } catch(NumberFormatException e) {
                for(String d : dict) {
                    s = s.toLowerCase();
                    String a[] = s.split("\\s");
                    if(a[0].equals(d)) {
                        isInt = false;
                        passed = true;
                    }
                }
            }
            if(!passed) {
                System.out.println("Your choice must be between " + min + " and " + max + ", or a valid command! For a list of commands, type /help");
            }

        } while(!passed);

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

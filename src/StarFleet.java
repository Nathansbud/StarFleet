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
            {"Hull", "Meme", "Machine"}, //3, Hull Options
            {"Barracks", "Testing", "123"}  //4, Barracks
    };

    private static String dict[] = {
            "/help",
    };


    public static void main(String[] args) {
        g = new GameManager();
        menuHistory.push(1);

        while(true) {
            generateMenu(menuState);
            String c = "";
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
                switch(c) {
                    default:
                        System.out.println("MEME");
                        break;
                }
            }
        }
    }

    public static void generateMenu(int choice) {
        if(choice < menu.length) {
            String[] list = menu[choice];

            System.out.println("[" + list[0] + "]");
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
                    if(s.equals(d)) {
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

//    public static int getBoundedIntChoice(int min, int max) {
//        System.out.print("Choose: ");
//        int r;
//
//        do {
//            while(!sc.hasNextInt()) {
//                System.out.print("Your input must be between " + min + " and " + max + ": ");
//                sc.nextLine();
//            }
//
//            r = sc.nextInt();
//            if(r == -1) {
//                break;
//            }
//            if(r > max || r < min) {
//                System.out.println("Your input must be between " + min + " and " + max + ": ");
//            }
//        } while(r > max || r < min);
//
//        return r;
//    }


}

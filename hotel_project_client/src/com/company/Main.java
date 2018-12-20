package com.company;


import java.util.Scanner;

public class Main {
    public static final int PORT = 3000;
    public static void main(String[] args) {
//Client


            //the all menu information and receive information from user
        int choice;
        while ((choice = printMenu()) != 5){
            switch (choice){
                case 1:
                    DiningRoom diningRoom = makeDiningRoom();
                    Server.addDiningRoom(diningRoom);
                    break;
                case 2:
                    StandardRoom standardRoom = makeStandardRoom();
                    Server.addStandardRoom(standardRoom);
                    break;
                case 3:
                    SuiteRoom suiteRoom = makeSuiteRoom();
                    Server.addSuiteRoom(suiteRoom);
                    break;
                case 4:
                    int getRoom = getRoomByNumber();
                    Server.getRoom(getRoom);
                    break;

            }
        }
        System.out.println("bye bye!");
    }


    private static int readIntegerFromConsole(String instruction){
        System.out.print(instruction);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int x = -1;
        try{
            x = Integer.valueOf(input);
        }catch (Exception ex){
            System.out.println("this is not an integer!");
            return readIntegerFromConsole(instruction);
        }
        return x;
    }
    private static double readDoubleFromConsole(String instruction){
        System.out.print(instruction);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double x = -1;
        try{
            x = Double.valueOf(input);
        }catch (Exception ex){
            System.out.println("this is not an double!");
            return readDoubleFromConsole(instruction);
        }
        return x;
    }

    private static int printMenu(){
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("please choose:");
        System.out.println("1. Add a Dining room");
        System.out.println("2. Add a Standard room ");
        System.out.println("3. Add a Suite room");
        System.out.println("4. Get room by number");
        System.out.println("5. Exit");
        int choice = readIntegerFromConsole("your choice: ");
        if(choice < 1 || choice > 5){
            System.out.println("invalid choice!");
            return printMenu();
        }
        return choice;
    }
    private static String getString(String string){
        System.out.println(string);
        Scanner s = new Scanner(System.in);
        String newString = s.nextLine();
        if (newString.length() < 2){
            System.out.println("Its must be at least 2 chars");
            return getString(string);
        }
        return newString;
    }

    private static DiningRoom makeDiningRoom(){
        String name = "Dining room";
        double area = readDoubleFromConsole("Type a area: ");
        String phoneNumber = getString("Add a phone number: ");
        int maxPeople = -1;
        while (maxPeople < 0 )
            maxPeople = readIntegerFromConsole("Add the maximum number of people: ");
        return new DiningRoom(name, area, phoneNumber, maxPeople);
    }

    private static StandardRoom makeStandardRoom(){
        String name = "Standard room";
        double area = readDoubleFromConsole("Type a area: ");
        String phoneNumber = getString("Add a phone number: ");
        int numOfBeds = -1;
        while (numOfBeds < 0 || numOfBeds > 5)
            numOfBeds = readIntegerFromConsole("Type number of beds: (Max 5 beds)");
        int intTypeBed = -1;
        while (intTypeBed < 1 || intTypeBed > 4)
            intTypeBed = readIntegerFromConsole("Enter type of bed: \n 1.SINGLE \n 2.DOUBLE \n3.TRIPLE \n4.KING SIZE");
        TypeBed typeBed = null;
        switch (intTypeBed){
            case 1:
                typeBed = TypeBed.SINGLE;
                break;
            case 2:
                typeBed = TypeBed.DOUBLE;
                break;
            case 3:
                typeBed = TypeBed.TRIPLE;
                break;
            case 4:
                typeBed = TypeBed.KING_SIZE;
                break;
        }
        int intDirection = -1;
        while (intDirection < 1 || intDirection > 5)
            intDirection = readIntegerFromConsole("Enter a direction: \n 1.WEST \n 2.NORTH \n3.SOUTH \n4.EAST \n5.SEA SIDE");
        Direction direction  = null;
        switch (intDirection){
            case 1:
                direction = Direction.WEST;
                break;
            case 2:
                direction = Direction.NORTH;
                break;
            case 3:
                direction = Direction.SOUTH;
                break;
            case 4:
                direction = Direction.EAST;
                break;
            case 5:
                direction = Direction.SEA_SIDE;
                break;
        }
        return new StandardRoom(name, area, phoneNumber, numOfBeds, typeBed, direction);
    }


    private  static SuiteRoom makeSuiteRoom(){
        String name = "Suite room";
        double area = readDoubleFromConsole("Type a area: ");
        String phoneNumber = getString("Add a phone number: ");
        int numOfBeds = -1;
        while (numOfBeds < 0 || numOfBeds > 5)
            numOfBeds = readIntegerFromConsole("Type number of beds: (Max 5 beds)");
        int luxuriousDegree = readIntegerFromConsole("Select a luxurious level between 1-5: ");
        while (luxuriousDegree < 1 || luxuriousDegree > 5) {
            System.out.println("you have to choose 1 - 5.");
            luxuriousDegree = readIntegerFromConsole("Select a luxurious level: ");
        }

        return new SuiteRoom(name, area, phoneNumber, numOfBeds, luxuriousDegree);
    }
    private static int getRoomByNumber (){
        int getRoom = readIntegerFromConsole("Enter number of room: ");
        return getRoom;
    }
}




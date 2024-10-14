//needed imports explicitly specified for performance and clarity
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    //Helper methods for main

    //Validates user input for CLI menu (menu options) -- doesn't validate data like names, phone numbers (would be better implemented with RegEx instead)
    private static String inputValidator(ArrayList<String> choicesArray, String printMessage) {
        Scanner userInput = new Scanner(System.in);
        String option;
        boolean validChoice = false;

        //runs until user enter an option in a valid range specified in choicesArray
        while (true) {
            System.out.println(printMessage);
            try {
                option = userInput.nextLine();
                for (String listItem : choicesArray) {
                    if (listItem.equals(option)) {
                        validChoice = true;
                        break;
                    }
                }
                if (!validChoice) {
                    throw new Exception("Sorry, the input you entered is wrong, please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return option;
    }

    //ensures that phone numbers are ten digit integers
    private static String phoneNumberValidator() {
        Scanner userInput = new Scanner(System.in);
        String option;
        boolean validChoice = false;

        //runs until user enter a valid option (string containing only 10 integers)
        while (true) {
            System.out.println("Please enter your phone number(10 consecutive integers): ");
            try {
                option = userInput.nextLine();
                if (option.chars().allMatch( Character::isDigit ) && (option.length() == 10)) {
                    validChoice = true;
                }
                if (!validChoice) {
                    throw new Exception("Sorry, the input you entered is wrong, please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return option;
    }

    //ensures that names aren't empty and don't include any special characters
    private static String nameValidator() {
        Scanner userInput = new Scanner(System.in);
        String option;
        boolean validChoice = false;

        //runs until user enter a valid option (string containing only latin characters ans spaces)
        while (true) {
            System.out.println("Please enter your name(only latin alphabetical numbers and spaces allowed): ");
            try {
                option = userInput.nextLine();
                if (    (option.length() != 0)
                        && !(Character.toString(option.charAt(0)).equals(" "))
                        && !(Character.toString(option.charAt(option.length()-1)).equals(" "))
                ) {
                    for (int i = 0; i < option.length(); i++) {
                        if (    (((option.charAt(i) >= 65) && (option.charAt(i) <= 90))
                                || (option.charAt(i) >= 97) && (option.charAt(i) <= 122))
                                || (Character.toString(option.charAt(i)).equals(" "))
                        ) {
                            validChoice = true;
                        }
                    }
                }
                if (!validChoice) {
                    throw new Exception("Sorry, the input you entered is wrong, please try again.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return option;
    }

    //ensures dates are entered in the correct format
    private static LocalDate isDateValid() {
        Scanner userInput = new Scanner(System.in);
        String option;
        boolean flag = false;
        LocalDate ld = null;
        System.out.println("Please enter the date you want to receive your order(dd/MM/yyyy): ");
        while (!flag) {
            DateTimeFormatter f = DateTimeFormatter.ofPattern ("dd/MM/uuuu");
            try {
                option = userInput.nextLine();
                ld = LocalDate.parse(option, f.withResolverStyle(ResolverStyle.STRICT));
                if (ld.isAfter(LocalDate.now())) {
                    flag = true;
                } else {
                    throw new Exception("Invalid date - enter a date in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println ("Wrong date format - please try again.");
            } catch (Exception e) {
                System.out.println (e.getMessage());
            }
        }
        return ld;
    }

    //returns an array populated with the right number of menu options (integers as strings) for user to choose from
    private static ArrayList<String> arrayListPopulator(int numberOfArguments) {
        ArrayList<String> validChoices = new ArrayList<>();
        for (int i = 0; i < numberOfArguments; i++) {
            validChoices.add(Integer.toString(i));
        }
        return validChoices;
    }

    //user prompted to buy or order device depending on available stock
    private static <T> boolean buyOrOrderDevice(HashMap<ElectronicDevice, Integer> Stock, String deviceNumber, HashMap<Integer, Sale> Sales, HashMap<Integer, Order> Orders, Class<T> electronicDevice) {
        //initialization of generic instance of type T - will be used to determine the class of the device (eg Tv, Dvd ect.)
        T electronicDeviceInstance = null;

        //Try catch block to catch any exceptions that might occur during the instantiation of the ElectronicDevice instance
        //a number of exceptions could occur so using for loop to catch any suppressed exceptions as well
        //Exceptions shouldn't happen and if they do the program terminates
        try {
            electronicDeviceInstance = electronicDevice.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + Arrays.toString(t.getSuppressed()));
            }

            System.out.println("This shouldn't had happened - exiting the program.");
            System.exit(-1);
        }

        int i = 1;
        String option;
        String printMessage;
        boolean flag = false;   //flag to indicate if the user decided not to buy/order the device

        //looping through the map
        for (Map.Entry<ElectronicDevice, Integer> entry : Stock.entrySet()) {
            //checks if the item in the map is the specified device type eg Tv
            if (entry.getKey().getClass().equals(electronicDeviceInstance.getClass())) {
                //checks if it is the item the user requested info upon
                if (Integer.toString(i).equals(deviceNumber)) {
                    //prints the item
                    System.out.println(entry.getKey().toString());
                    //if the stock is greater equal to 1 (there is at least one available device in stock) prompts the user to buy it
                    //else to order it
                    if (entry.getValue() >= 1) {
                        printMessage = "Press 0 to buy the: " + entry.getKey().getModel() + " or press 1.";
                        option = inputValidator(arrayListPopulator(2), printMessage);
                        if (option.equals("0")) {
                            //calls makeSale to make the purchase
                            makeSale(entry.getKey(), Stock, Sales);
                        } else {
                            flag = true;
                        }
                    } else {
                        printMessage = "Press 0 to order the: " + entry.getKey().getModel() + " or press 1.";
                        option = inputValidator(arrayListPopulator(2), printMessage);
                        if (option.equals("0")) {
                            //calls makeOrder to order
                            makeOrder(entry.getKey(), Stock, Orders);
                        } else {
                            flag = true;
                        }
                    }
                    break;
                }
                i++;
            }
        }
        return flag;
    }

    //requests the appropriate info to make the order, makes the order and updates the orders map
    //order id automatically updated in Order class for error minification
    private static void makeOrder(ElectronicDevice key, HashMap<ElectronicDevice, Integer> stock, HashMap<Integer, Order> Orders) {
        stock.put(key, stock.get(key) - 1);
        String name = nameValidator();
        String phoneNumber = phoneNumberValidator();
        LocalDate ld = isDateValid();
        Order newOrder = new Order(key, name, phoneNumber, ld);
        Orders.put(newOrder.getId(), newOrder);
    }

    //requests the appropriate info to make the order, makes the sale and updates the orders map
    //sale id automatically updated in Sale class for error minification
    private static void makeSale(ElectronicDevice key, HashMap<ElectronicDevice, Integer> stock, HashMap<Integer, Sale> Sales) {
        stock.put(key, stock.get(key) - 1);
        String name = nameValidator();
        String phoneNumber = phoneNumberValidator();
        Sale newSale = new Sale(key, name, phoneNumber);
        Sales.put(newSale.getId(), newSale);
    }

    //finds specified order in map and prints its info
    private static void printOrder(String option, HashMap<Integer,Order> orders) {
        int numberOfOrder = 1;
        for (Map.Entry<Integer, Order> entry : orders.entrySet()) {
            if (Integer.toString(numberOfOrder).equals(option)) {
                System.out.println(entry.getValue().toString());
                break;
            }
            numberOfOrder++;
        }
    }

    //finds specified sale in map and prints its info
    private static void printSale(String option, HashMap<Integer, Sale> sales) {
        int numberOfOrder = 1;
        for (Map.Entry<Integer, Sale> entry : sales.entrySet()) {
            if (Integer.toString(numberOfOrder).equals(option)) {
                System.out.println(entry.getValue().toString());
                break;
            }
            numberOfOrder++;
        }
    }

    //prints devices of a specified type (eg Tvs) and returns the total number of these devices back (-1 to count from 0)
    private static <T> int printStockAndReturnNumberOfSpecifiedDevices(HashMap<ElectronicDevice, Integer> Stock, Class<T> electronicDevice) {
        //uses generic instance of T and instantiates it to null - will be used for identification of the type of the device
        T electronicDeviceInstance = null;
        //try-catch block to catch any unexpected errors while re-instantiating the device item with class passes as parameter
        //if any errors happen exit the program
        try {
            electronicDeviceInstance = electronicDevice.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + Arrays.toString(t.getSuppressed()));
            }

            System.out.println("This shouldn't had happened - exiting the program.");
            System.exit(-1);
        }

        int stockOfSpecifiedDevices = 1;
        for (Map.Entry<ElectronicDevice, Integer> entry : Stock.entrySet()) {
            if (entry.getKey().getClass().equals(electronicDeviceInstance.getClass())) {
                System.out.println(stockOfSpecifiedDevices + ": " + entry.getKey().getModel());
                stockOfSpecifiedDevices++;
            }
        }
        return stockOfSpecifiedDevices - 1;
    }

    //prints orders or sales (whichever is specified) and returns their total number
    private static <T> int printOrdersOrSalesAndReturnNumber(HashMap<Integer, Order> Orders, HashMap<Integer, Sale> Sales, Class<T> orderOrSale) {
        //same usage of class T as in printStockAndReturnNumberOfSpecifiedDevices
        T orderOrSaleInstance = null;
        try {
            orderOrSaleInstance = orderOrSale.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                System.out.println("Suppressed: " + Arrays.toString(t.getSuppressed()));
            }

            System.out.println("This shouldn't had happened - exiting the program.");
            System.exit(-1);
        }
        //checks if we are printing orders or sales
        int numberOfOrderOrSale = 1;
        if (orderOrSaleInstance.getClass().equals(Order.class)) {
            for (Map.Entry<Integer, Order> entry : Orders.entrySet()) {
                System.out.println(numberOfOrderOrSale + ": " + entry.getValue().getId());
                numberOfOrderOrSale++;
            }
        } else {
            for (Map.Entry<Integer, Sale> entry : Sales.entrySet()) {
                System.out.println(numberOfOrderOrSale + ": " + entry.getValue().getId());
                numberOfOrderOrSale++;
            }
        }
        //prints message if there no orders/sales
        if (numberOfOrderOrSale == 1) {
            if (orderOrSaleInstance.getClass().equals(Order.class)) {
                System.out.println("No orders have been placed.\n");
            } else {
                System.out.println("No sales have been placed.\n");
            }
            numberOfOrderOrSale = 0;
        }
        return numberOfOrderOrSale;
    }

    //prints the order the user requested and returns its number (used if the user wants to buy the ordered device)
    private static int printAndReturnNumberOfOrderInMap(String option, HashMap<Integer, Order> orders) {
        int numberOfOrder = 0;
        for (Map.Entry<Integer, Order> entry : orders.entrySet()) {
            if (Integer.toString(numberOfOrder).equals(option)) {
                System.out.println(entry.getValue().toString());
                break;
            }
            numberOfOrder++;
        }
        return numberOfOrder;
    }

    public static void main(String[] args) {
        //initializing ids of orders and sales
        Sale.initializeNumberOfOrders();
        Order.initializedNumberOfOrders();

        //instantiating  two of each device type
        //AudioVisual --> Tvs
        Tv Lg = new Tv(1000, "LG", 2009, "LG", 102.3, "LED", "HD", "HDMI", "1980x2060");
        Tv TOSHIBA = new Tv(1001, "TOSHIBA", 2019, "TOSHIBA", 400.5, "LCD", "4K", "HDMI,DVI", "3840x2160");
        //AudioVisual --> Dvds
        Dvd dvd1 = new Dvd(2000, "Acer", 2010, "Acer", 100, "BlueRay", "BD-R", "1980x2060");
        Dvd dvd2 = new Dvd(2001, "Sony", 2008, "Sony", 95, "DVD", "DVD-RW", "7680x4320");
        //AudioVisual --> Cameras
        Camera Nikond3500 = new Camera(2000, "Acer", 2010, "Acer", 100, "BlueRay", 20, 3.5, 7, 5);
        Camera Canons500 = new Camera(2001, "Sony", 2008, "Sony", 95, "DVD", 30, 4.5, 6, 5);

        //Gaming --> Consoles
        Console PS4 = new Console(3000, "Sony", 2016, "Sony", 200, "PS4", "AMD Jaguar", "AMD Radeon", "High Quality", 500);
        Console PS3 = new Console(3001, "Sony", 2013, "Sony", 150, "PS3", "Cell Broadband Engine", "RSX", "High Quality", 80);
        //Gaming --> Mobile Consoles
        MobileConsole PSP = new MobileConsole(4000, "Sony", 2010, "Sony", 100, "PSP", "MIPS32 R4k-base ", "Cell Broadband Engine", "RSX", 20);
        MobileConsole PSVita = new MobileConsole(4001, "Sony", 2014, "Sony", 254.3, "PSVita", " ARM Cortex-A9 MPCore ", "  SGX543MP", "Stereo speakers", 20);

        //HomeDevices --> Washing Machines
        WashingMachine Hisense = new WashingMachine(5000, "WFHV7012", 2018, "Hisense", 299.9, "A+++", 1200, 54);
        WashingMachine Indesit = new WashingMachine(5001, "BWSA61253 ", 2020, "Indesit", 350, "A+++", 2000, 60.5);
        //HomeDevices --> Fridges
        Fridge Davoline = new Fridge(6000, "F85 WH", 2018, "Davoline", 169, "A+", "2-door", 88, 14);
        Fridge Morris = new Fridge(6001, "S70131SP", 2019, "Morris", 200, "A++", "1-door", 80, 12);
        //Creating maps for available devices, orders and sales -- chosen for no duplicates, key-value pairs and performance
        HashMap<ElectronicDevice, Integer> Stock = new HashMap<>();
        HashMap<Integer, Order> Orders = new HashMap<>();
        HashMap<Integer, Sale> Sales = new HashMap<>();
        //populating stock map
        Stock.put(Lg, 0);
        Stock.put(TOSHIBA, 1);
        Stock.put(dvd1, 2);
        Stock.put(dvd2, 3);
        Stock.put(Nikond3500, 2);
        Stock.put(Canons500, 3);
        Stock.put(PS4, 5);
        Stock.put(PS3, 3);
        Stock.put(PSP, 2);
        Stock.put(PSVita, 3);
        Stock.put(Hisense, 6);
        Stock.put(Indesit, 4);
        Stock.put(Davoline, 2);
        Stock.put(Morris, 3);
        //declaring necessary variables
        String option; //the user input
        String printMessage; //message to print to user
        int stockItemsOfClass; //passed as parameter to arrayListPopulator, depending upon number of a device type in Stock
        //flag value needed to re-request user input at an "exit" option
        boolean loopFlag = true; //flag used to be able to go back a level in some edge cases

        while (true) {
            if (loopFlag) {
                printMessage = "Choose an option:\n0 = Available devices.\n1 = Orders made.\n2 = Orders completed.\n3 = Exit program.";
                option = inputValidator(arrayListPopulator(4), printMessage);
                loopFlag = false;
            } else {
                //user going back a level while already in audiovisual devices -- needed to function properly
                option = "0";
            }

            programLoop:
            switch (option) {
                case "0":
                    printMessage = "Choose an option:\n0 = Audiovisual devices.\n1 = Gaming devices.\n2 = Home devices.\n3 = Go back one level.";
                    option = inputValidator(arrayListPopulator(4), printMessage);
                    switch (option) {
                        case "0":
                            while (true) {
                                printMessage = "Choose an option:\n0 = TV.\n1 = DVD.\n2 = Camera.\n3 = Go back one level";
                                option = inputValidator(arrayListPopulator(4), printMessage);

                                switch (option) {
                                    case "0":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a TV from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, Tv.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, Tv.class);
                                            if (!flag) {
                                                //if the user buys or orders the device, the method will return false and thus
                                                //will get in the if and return to the starting menu option
                                                //else it will return true in order to bypass the if statement and just
                                                //give the option to re-choose a device
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "1":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a DVD from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, Dvd.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, Dvd.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "2":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a Camera from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, Camera.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, Camera.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "3":
                                        break programLoop;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + option);
                                }
                            }
                        case "1":
                            while (true) {
                                printMessage = "Choose an option:\n0 = Consoles.\n1 = Mobile Consoles.\n2 = Go back one level";
                                option = inputValidator(arrayListPopulator(3), printMessage);
                                switch (option) {
                                    case "0":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a Console from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, Console.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, Console.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "1":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a Mobile Console from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, MobileConsole.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, MobileConsole.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "2":
                                        break programLoop;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + option);
                                }
                            }
                        case "2":
                            while (true) {
                                printMessage = "Choose an option:\n0 = Washing Machines.\n1 = Fridges.\n2 = Go back one level";
                                option = inputValidator(arrayListPopulator(3), printMessage);
                                switch (option) {
                                    case "0":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a Washing Machine from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, WashingMachine.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, WashingMachine.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "1":
                                        while (true) {
                                            printMessage = "Press 0 to go back one level or choose a Fridge from the list:";
                                            stockItemsOfClass = printStockAndReturnNumberOfSpecifiedDevices(Stock, Fridge.class);
                                            option = inputValidator(arrayListPopulator(stockItemsOfClass + 1), printMessage);
                                            if (option.equals("0")) {
                                                break;
                                            }
                                            boolean flag = buyOrOrderDevice(Stock, option, Sales, Orders, Fridge.class);
                                            if (!flag) {
                                                loopFlag = true;
                                                break programLoop;
                                            }
                                        }
                                        break;
                                    case "2":
                                        break programLoop;
                                    default:
                                        throw new IllegalStateException("Unexpected value: " + option);
                                }
                            }
                        case "3":
                            loopFlag = true;
                            break programLoop;
                        default:
                            throw new IllegalStateException("Unexpected value: " + option);
                    }
                case "1":
                    while (true) {
                        printMessage = "Choose an option:\n0 = See orders.\n1 = Go back one level.";
                        option = inputValidator(arrayListPopulator(2), printMessage);
                        switch (option) {
                            case "0":
                                int numberOfOrders = printOrdersOrSalesAndReturnNumber(Orders, Sales, Order.class);
                                if (numberOfOrders == 0) {
                                    //exit if there are no orders
                                    loopFlag = true;
                                    break programLoop;
                                }
                                while (true) {
                                    printMessage = "Press 0 to go back one level or choose an Order from the list:\n";
                                    option = inputValidator(arrayListPopulator(numberOfOrders), printMessage);
                                    if (option.equals("0")) {
                                        break;
                                    } else {
                                        printOrder(option, Orders);
                                        int numberOfOrderInMap = printAndReturnNumberOfOrderInMap(option, Orders);
                                        printMessage = "Press 0 to receive your order or 1 to go back.";
                                        option = inputValidator(arrayListPopulator(2), printMessage);
                                        if (option.equals("0")) {
                                            makeSale(Orders.get(numberOfOrderInMap).getDevice(), Stock, Sales);
                                            Orders.remove(numberOfOrderInMap);
                                            loopFlag = true;
                                            break programLoop;
                                        }
                                    }
                                }
                                break;
                            case "1":
                                loopFlag = true;
                                break programLoop;
                        }
                    }
                case "2":
                    while (true) {
                        printMessage = "Choose an option:\n0 = See sales.\n1 = Go back one level.";
                        option = inputValidator(arrayListPopulator(2), printMessage);
                        switch (option) {
                            case "0":
                                int numberOfSales = printOrdersOrSalesAndReturnNumber(Orders, Sales, Sale.class);
                                if (numberOfSales == 0) {
                                    loopFlag = true;
                                    break programLoop;
                                }
                                printMessage = "Press 0 to go back one level or choose a Sale from the list:\n";
                                option = inputValidator(arrayListPopulator(numberOfSales), printMessage);
                                if (option.equals("0")) {
                                    break;
                                } else {
                                    printSale(option, Sales);
                                    loopFlag = true;
                                    break programLoop;
                                }
                            case "1":
                                loopFlag = true;
                                break programLoop;
                        }
                    }
                case "3":
                    printMessage = "Are you sure you want to terminate the program?\n0 = Yes.\n1 = No.";
                    option = inputValidator(arrayListPopulator(2), printMessage);
                    if ("0".equals(option)) {
                        System.exit(-1);
                    }
                    loopFlag = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + option);
            }
        }
    }
}

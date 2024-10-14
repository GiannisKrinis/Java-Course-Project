import java.time.LocalDate;
import java.util.Date;

public class Sale {
    private static int numberOfOrders;
    private final int ID;
    private final String MODEL;
    private final String NAME;
    private final String PHONE_NUMBER;
    private final LocalDate DATE;
    private final double COST;
    private final ElectronicDevice DEVICE;
    
    //Default Constructor
    public Sale () {
        this.DEVICE = null;
        this.DATE = null;
        this.ID = 0;
        this.MODEL = null;
        this.NAME = null;
        this.PHONE_NUMBER = null;
        this.COST = 0;
    }
    
    //2nd Constructor
    public Sale(ElectronicDevice device, String name, String phoneNumber) {
        this.MODEL = device.getModel(); //Initialization MODEL
        this.NAME = name;   //Initialization NAME
        this.PHONE_NUMBER = phoneNumber;    //Initialization PHONE_NUMBER
        this.DATE = LocalDate.now();     //Initialization DATE
        this.COST = device.getPrice() - (device.getPrice() * (device.getDiscount() / 100)); //Initialization COST
        Sale.numberOfOrders++;  // Increase the static attribute by 1
        this.ID = getNumberOfOrders();   //Initialization ID
        this.DEVICE = device;    //Initialization DEVICE
    }
    
    //METHODS
    
    //Getter
    public static int getNumberOfOrders() {
        return numberOfOrders;   //Returns Sale's numberOfOrders
    }
    
    //Getter
    public int getId() {
        return ID;   //Returns Sale's ID
    }
    
    //This static method initialized every time the numberOfOrders as 0
    public static void initializeNumberOfOrders() {
        Sale.numberOfOrders = 0;
    }
    
    //Getter
    public String getModel() {
        return MODEL;    //Returns Sale's MODEL
    }
    
    //Getter
    public String getName() {
        return NAME;     //Returns Sale's NAME
    }
    
    //Getter
    public String getPhoneNumber() {
        return PHONE_NUMBER;     //Returns Sale's PHONE_NUMBER
    }
    
    //Getter
    public LocalDate getDate() {
        return DATE;     //Returns Sale's DATE
    }
    
    //Getter
    public double getCost() {
        return COST;    //Returns Sale's COST
    }
    
    //Getter
    public ElectronicDevice getDevice() {
        return DEVICE;  //Returns Sale's DEVICE
    }
    
    //ToString Method
    @Override
    public String toString() {
        return  "Sale ID: " + ID +
                "\nModel: " + MODEL +
                "\nName: " + NAME +
                "\nPhone number: " + PHONE_NUMBER +
                "\nDate: " + DATE +
                "\nFinal cost: " + COST +
                "\nDevice: " + DEVICE.toString();
    }
}

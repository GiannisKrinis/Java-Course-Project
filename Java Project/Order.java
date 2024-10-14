import java.time.LocalDate;
import java.util.Date;

public class Order {
    //Instance Attributes
    private static int numberOfOrders;
    private final int ID;
    private final String MODEL;
    private final String NAME;
    private final String PHONE;
    private final LocalDate ORDER_DATE;
    private final LocalDate ARRIVAL_DATE;
    private final double COST;
    private final ElectronicDevice DEVICE;

    //Default Constructor
    public Order () {
        this.DEVICE = null;
        this.ARRIVAL_DATE = null;
        this.ORDER_DATE = null;
        this.ID = 0;
        this.MODEL = null;
        this.NAME = null;
        this.PHONE = null;
        this.COST = 0;
    }

    //2nd Constructor
    public Order(ElectronicDevice device, String name, String phone, LocalDate arrivalDate){
        this.MODEL = device.getModel(); //Initialization MODEL
        this.NAME = name; //Initialization NAME
        this.PHONE = phone; //Initialization PHONE
        this.ORDER_DATE = LocalDate.now(); //Initialization ORDER_DATE
        this.ARRIVAL_DATE = arrivalDate; //Initialization ARRIVAL_DATE
        this.COST = device.getPrice() - (device.getPrice() * (device.getDiscount() / 100)); //Initialization COST
        Order.numberOfOrders++; // Increase the static attribute by 1
        this.ID = getNumberOfOrders(); //Initialization ID
        this.DEVICE = device; //Initialization DEVICE
    }
    
    //METHODS
    
    //Getter
    public static int getNumberOfOrders(){
        return numberOfOrders; //Returns Order's numberOfOrders
    }
    
    //Getter
    public int getId(){
        return ID;//Returns Order's ID
    }
    
    //This static method initialized every time the numberOfOrders as 0
    public static void initializedNumberOfOrders(){
        Order.numberOfOrders = 0;
    }
    
     //Getter
    public String getModel(){
        return MODEL;//Returns Order's MODEL
    }
    
    //Getter
    public String getName(){
        return NAME;//Returns Order's NAME
    }
    
    //Getter
    public String getPhone(){
        return PHONE;   //Returns Order's PHONE
    }

    public LocalDate getOrderDate(){
        return ORDER_DATE; //Returns Order's ORDER_DATE
    }
    
    //Getter
    public LocalDate getArrivalDate(){
        return ARRIVAL_DATE; //Returns Order's ARRIVAL_DATE
    }
    
    //Getter
    public double getCost(){
        return COST;    //Returns Order's COST
    }
    
    //Getter
    public ElectronicDevice getDevice() {
        return DEVICE;  //Returns Order's DEVICE
    }
    
    //ToString Method
    @Override
    public String toString(){
        return "Order ID: " + ID +
                "\nModel: " + MODEL +
                "\nName: " + NAME +
                "\nPhone: " + PHONE +
                "\nOrder Date: " + ORDER_DATE +
                "\nArrival Date: " + ARRIVAL_DATE +
                "\nFinal cost: " + COST +
                "\nDevice: " + DEVICE.toString();
    }
}